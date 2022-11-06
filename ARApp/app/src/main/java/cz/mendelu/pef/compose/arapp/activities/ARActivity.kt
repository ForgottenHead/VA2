package cz.mendelu.pef.compose.arapp.activities

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.google.ar.core.HitResult
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.BaseArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.gorisse.thomas.sceneform.scene.await
import cz.mendelu.pef.compose.arapp.R
import cz.mendelu.pef.compose.arapp.ar.ARMainFragment
import cz.mendelu.pef.compose.arapp.databinding.ActivityAractivityBinding
import cz.mendelu.pef.compose.arapp.utils.ARUtils
import kotlinx.coroutines.launch

class ARActivity : AppCompatActivity(), Scene.OnUpdateListener,
    BaseArFragment.OnTapArPlaneListener {

    private lateinit var binding: ActivityAractivityBinding
    private lateinit var arFragment: ARMainFragment

    private var model: Renderable? = null
    private var anchorNode: AnchorNode? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAractivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        arFragment = ARMainFragment()
        supportFragmentManager.commit {
            add(R.id.containerFragment, arFragment)
        }

        arFragment.setOnTapPlaneListener(this)
        arFragment.setOnSceneChangeListener(this)

        lifecycleScope.launch{
            loadModel("models/halloween.glb")
        }

    }

    private suspend fun loadModel(path: String){
        model = ModelRenderable.builder()
            .setSource(this, Uri.parse(path))
            .setIsFilamentGltf(true)
            .await()
    }

    override fun onUpdate(frameTime: FrameTime?) {
        val frame = arFragment.getFrame()
        frame?.let {
            val camera = it.camera
            if (camera.trackingState == TrackingState.TRACKING && anchorNode != null){
                val cameraPosition = camera.displayOrientedPose
                val distance = ARUtils
                    .getDistanceToObject(cameraPosition, anchorNode!!.anchor!!.pose)
                //Log.i("Distance: ", distance.toString())
                //anchorNode!!.localScale = Vector3(distance, distance, distance)
            }

        }
    }

    override fun onTapPlane(
        hitResult: HitResult?,
        plane: com.google.ar.core.Plane?,
        motionEvent: MotionEvent?
    ) {
        if (model == null){
            return
        }

        if (anchorNode == null){
            val node = createNode(hitResult)
            node?.let {
                arFragment.addModel(it)
            }
        }



    }

    private fun createNode(hitResult: HitResult?): Node?{
        hitResult?.let {
            val anchor = it.createAnchor()
            anchorNode = AnchorNode(anchor)
            anchorNode!!.localScale = Vector3(0.2f, 0.2f, 0.2f)

            val transformableNode = TransformableNode(arFragment.getTransformationSystem())
            transformableNode.renderable = model
            transformableNode.renderableInstance.animate(true)
            transformableNode.localPosition = Vector3(0.0f, 0.5f, -0.5f)


            anchorNode!!.addChild(transformableNode)
            return anchorNode
        }
        return null
    }

}
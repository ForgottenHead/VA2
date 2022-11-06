package cz.mendelu.pef.compose.arapp.ar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.ar.core.Config
import com.google.ar.core.Frame
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.SceneView
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.CameraStream
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.BaseArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.ux.TransformationSystem
import com.gorisse.thomas.sceneform.scene.await
import cz.mendelu.pef.compose.arapp.R
import cz.mendelu.pef.compose.arapp.activities.ARActivity


class ARMainFragment : Fragment(R.layout.layout_ar_fragment) {

    private lateinit var arFragment: ArFragment
    private val arSceneView get() = arFragment.arSceneView
    private val scene get() = arSceneView.scene

    private lateinit var onTapArPlaneListener: BaseArFragment.OnTapArPlaneListener
    private lateinit var onUpdateListener: Scene.OnUpdateListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arFragment = (childFragmentManager.findFragmentById(R.id.arFragment) as ArFragment).apply {
            setOnSessionConfigurationListener { session, config ->
                if (session.isDepthModeSupported(Config.DepthMode.AUTOMATIC)) {
                    config.depthMode = Config.DepthMode.AUTOMATIC
                }
            }
            setOnViewCreatedListener { arSceneView ->
                arSceneView.setFrameRateFactor(SceneView.FrameRate.FULL)
                arSceneView.cameraStream.depthOcclusionMode =
                    CameraStream.DepthOcclusionMode.DEPTH_OCCLUSION_ENABLED
                arSceneView.scene.addOnUpdateListener(onUpdateListener)
            }
            setOnTapArPlaneListener(onTapArPlaneListener)
        }
    }

    fun setOnTapPlaneListener(onTapArPlaneListener: BaseArFragment.OnTapArPlaneListener){
        this.onTapArPlaneListener = onTapArPlaneListener
    }

    fun setOnSceneChangeListener(onUpdateListener: Scene.OnUpdateListener){
        this.onUpdateListener = onUpdateListener
    }

    fun addModel(child: Node){
        scene.addChild(child)
    }

    fun getTransformationSystem(): TransformationSystem{
        return arFragment.transformationSystem
    }

    fun getFrame(): Frame? = arFragment.arSceneView.arFrame
}
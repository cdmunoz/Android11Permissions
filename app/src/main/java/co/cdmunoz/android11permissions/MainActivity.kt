package co.cdmunoz.android11permissions

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import co.cdmunoz.android11permissions.databinding.ActivityMainBinding
import co.cdmunoz.android11permissions.utils.snackBar

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val cameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            with(binding.root) {
                when {
                    granted -> snackBar("Permission granted!")
                    shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                        //this option is available starting in API 23
                        snackBar("Permission denied, show more info!")
                    }
                    else -> snackBar("Permission denied")
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cameraIcon.setOnClickListener {
            cameraPermission.launch(Manifest.permission.CAMERA)
            //openSettings()
        }
    }

    private fun openSettings() {
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            data = Uri.parse("package:$packageName")
        }.run(::startActivity)
    }
}

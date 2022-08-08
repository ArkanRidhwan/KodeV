package com.example.latihan15.ui.dicoding

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.latihan15.core.data.Resource
import com.example.latihan15.databinding.FragmentAddStoryBinding
import com.example.latihan15.utils.createCustomTempFile
import com.example.latihan15.utils.reduceFileImage
import com.example.latihan15.utils.uriToFile
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class AddStoryFragment : Fragment() {

    private lateinit var binding: FragmentAddStoryBinding
    private var getFile: File? = null
    private lateinit var currentPhotoPath: String
    private val viewModel: StoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddStoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCamera.setOnClickListener { startCamera() }
        binding.btnGaleri.setOnClickListener { startGaleri() }
        binding.btnUpload.setOnClickListener {
            uploadImage()
        }

        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()

        }

        fun allPermissionGranted() = REQUIRED_PERMISSION.all {
            ContextCompat.checkSelfPermission(
                requireActivity().baseContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }

        if (!allPermissionGranted()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSION,
                REQUEST_CODE_PERMISSION
            )
        }
    }

    private fun uploadImage() {
        if (getFile != null) {
            binding.apply {
                val file = reduceFileImage(getFile as File)
                val description = etDescription.text.toString()
                val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val imageMultipart: MultipartBody.Part =
                    MultipartBody.Part.createFormData(
                        "photo",
                        file.name,
                        requestImageFile
                    )
                if (description.isNotEmpty()) {
                    val descriptionRequestBody =
                        description.toRequestBody("text/plain".toMediaTypeOrNull())

                    viewModel.postStory(
                        "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLWRIVHp2UUtDWTVuYXotejkiLCJpYXQiOjE2NTg4OTk0MDN9.0OOS-9zckZBV2VA55i8MW7a3DUKaC_8BBwsAGIBNzUY",
                        imageMultipart,
                        descriptionRequestBody
                    ).observe(viewLifecycleOwner) {
                        if (it != null) {
                            when (it) {
                                is Resource.Loading -> {
                                    binding.progressBar2.visibility = View.VISIBLE
                                }
                                is Resource.Success -> {
                                    binding.progressBar2.visibility = View.GONE
                                    requireActivity().onBackPressed()
                                }
                                is Resource.Error -> {
                                    binding.progressBar2.visibility = View.GONE
                                    Snackbar.make(requireView(), "Gagal", Snackbar.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    }
                }
            }
        } else {
            Toast.makeText(requireActivity(), "Gagal Juga", Toast.LENGTH_SHORT).show()
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == AppCompatActivity.RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile
            val result = BitmapFactory.decodeFile(myFile.path)
            binding.ivGambar.setImageBitmap(result)
        }
    }

    private fun startCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        intent.resolveActivity(requireActivity().packageManager)
        createCustomTempFile(requireActivity().application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireActivity(),
                "com.example.latihan15",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg: Uri = it.data?.data as Uri
            val myFile = uriToFile(selectedImg, requireActivity())
            getFile = myFile
            binding.ivGambar.setImageURI(selectedImg)
        }
    }

    private fun startGaleri() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a picture")
        launcherIntentGallery.launch(chooser)
    }

    companion object {
        private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSION = 10
    }
}
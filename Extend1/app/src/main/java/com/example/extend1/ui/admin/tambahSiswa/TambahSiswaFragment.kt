package com.example.extend1.ui.admin.tambahSiswa

import android.app.Activity
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.extend1.R
import com.example.extend1.databinding.FragmentTambahSiswaBinding
import com.example.extend1.model.Student
import com.example.extend1.utils.encodeImage
import com.example.extend1.utils.gone
import com.example.extend1.utils.showToast
import com.example.extend1.utils.visible
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.File


class TambahSiswaFragment : Fragment() {

    private val studentViewModel: StudentViewModel by viewModels()
    private lateinit var binding: FragmentTambahSiswaBinding
    private var encodedImage: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTambahSiswaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView3.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }

        binding.button.setOnClickListener {
            binding.apply {
                progressCircular.visible()
                button.gone()
                val name = editTextTextPersonName.text.toString()
                val job = editTextTextPersonName2.text.toString()
                val age = editTextTextPersonName3.text.toString()
                val student = Student(
                    name = name,
                    job = job,
                    age = age,
                    image = encodedImage.toString(),
                )
                studentViewModel.saveStudent(student).observe(viewLifecycleOwner) {
                    if (it) {
                        requireContext().showToast("Data Siswa Berhasil Dimasukan")
                        requireActivity().onBackPressed()
                    } else {
                        requireContext().showToast("Data Siswa Gagal Dimasukan")
                        progressCircular.gone()
                        button.visible()
                    }
                }
            }
        }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            when (resultCode) {
                Activity.RESULT_OK -> {
                    binding.apply {
                        try {
                            val imageUri = data?.data
                            val file = imageUri?.toFile() as File
                            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                ImageDecoder.decodeBitmap(ImageDecoder.createSource(file))
                            } else {
                                MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    imageUri
                                )
                            }
                            binding.apply {
                                Glide.with(requireActivity())
                                    .load(imageUri)
                                    .circleCrop()
                                    .into(imageView3)
                                encodedImage = encodeImage(bitmap)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                ImagePicker.RESULT_ERROR -> {
                    requireContext().showToast(ImagePicker.getError(data))
                }
                else -> {
                    requireContext().showToast(getString(R.string.canceled))
                }
            }
        }


}
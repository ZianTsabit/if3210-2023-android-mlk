package com.example.malika

import android.R
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.budiyev.android.codescanner.*
import com.example.malika.databinding.ActivityCodeScannerBinding
import retrofit2.Response

class CodeScannerActivity : AppCompatActivity() {

    lateinit var binding : ActivityCodeScannerBinding
    private lateinit var mCartViewModel: CartViewModel
    private lateinit var viewModel: CodeScannerViewModel

    lateinit var codeScanner : CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodeScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // activate scanner
        codeScanner()

        // ask camera permission
        setPermission()

        // get paymentStatus
        val repository = RetrofitRepository()
        val viewModelFactory = CodeScannerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CodeScannerViewModel::class.java)

        mCartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

        val totalPrice = Observer<Int> { newTotal ->
            if(newTotal === null) {
                binding.totalPrice.text = "Total: 0"
            }else {
                binding.totalPrice.text = "Total: Rp$newTotal"
            }
        }

        mCartViewModel.totalPrice.observe(this, totalPrice)

        val status = Observer<Response<PaymentStatus>> { newStatus ->
            if(newStatus.isSuccessful) {
                Log.d("Main", newStatus.body()!!.status)
                Log.d("Main", newStatus.code().toString())
                Log.d("Main", newStatus.message())

                if(newStatus.body()!!.status == "SUCCESS") {
                    binding.status.text = "Berhasil"
                    binding.status2.text = "Sudah dibayar"
                }else {
                    binding.status.text = "Gagal"
                    binding.status2.text = "Belum dibayar"
                }
            }else {
                binding.status.text = "Gagal"
                binding.status2.text = "Belum dibayar"
            }
        }
        viewModel.currentPaymentStatus.observe(this, status)
    }

    private fun codeScanner() {
        codeScanner = CodeScanner(this, binding.scanner)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {

                    viewModel.getPaymentStatus(it.text).toString()

                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Toast.makeText(applicationContext, "${it.message}", Toast.LENGTH_SHORT).show()
                }
            }

            binding.scanner.setOnClickListener {

                codeScanner.startPreview()

            }
        }

    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        super.onPause()
        codeScanner.startPreview()
    }

    private fun setPermission() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if(permission != PackageManager.PERMISSION_GRANTED) {
            makeReq()
        }
    }

    private fun makeReq() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CAMERA), 101
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permission: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults)
        when(requestCode) {
            101 -> {
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission Needed", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun backtoMenu() {
//
//        val intent = Intent(this, FoodFragment::class.java)
//        startActivity(intent)
//
//    }




}
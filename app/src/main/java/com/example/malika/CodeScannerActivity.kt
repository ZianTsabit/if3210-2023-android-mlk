package com.example.malika

import android.R
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.nfc.Tag
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
import kotlinx.coroutines.delay
import retrofit2.Response
import java.util.*

class CodeScannerActivity : AppCompatActivity() {

    lateinit var binding : ActivityCodeScannerBinding
    private lateinit var mCartViewModel: CartViewModel
    private lateinit var viewModel: CodeScannerViewModel
    private val foodFragment = FoodFragment()

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
                binding.totalPrice.text = "Total: IDR $newTotal"
            }
        }

        mCartViewModel.totalPrice.observe(this, totalPrice)

        val status = Observer<Response<PaymentStatus>> { newStatus ->

            if(newStatus.isSuccessful) {
                if(newStatus.body()!!.status == "SUCCESS") {
                    binding.status.text = "Berhasil"
                    binding.status2.text = "Sudah dibayar"
                    binding.imageView.setImageResource(com.example.malika.R.drawable.baseline_check_circle_24)

                    val task = object : TimerTask() {
                        override fun run() {
                            // code to be executed after the delay
                            val intent = Intent(this@CodeScannerActivity, MainActivity::class.java)
                            startActivity(intent)
                            mCartViewModel.deleteAllItem()
                        }
                    }

                    Timer().schedule(task,5000)

                }else {
                    binding.status.text = "Gagal"
                    binding.status2.text = "Belum dibayar"
                    binding.imageView.setImageResource(com.example.malika.R.drawable.baseline_cancel_24)
                }
            }else {
                binding.status.text = "Gagal"
                binding.status2.text = "Belum dibayar"
                binding.imageView.setImageResource(com.example.malika.R.drawable.baseline_cancel_24)
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
            scanMode = ScanMode.SINGLE
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
}
package com.example.pdf_viewer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import com.example.pdf_viewer.databinding.ActivityStoragePdfBinding
import com.github.barteksc.pdfviewer.PDFView
import java.io.File

class StoragePdfActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoragePdfBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoragePdfBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /*binding.pdfViewer.fromAsset("g435-wireless-gaming-headset-qsg.pdf").load()
*/
        binding.addButton.setOnClickListener {
            selectPdfFromStorage()
        }

        binding.nextButton.setOnClickListener {

            if (binding.pdfViewer.currentPage!= binding.pdfViewer.pageCount-1){
                binding.pdfViewer.jumpTo(binding.pdfViewer.currentPage+1)
            }
        }

        binding.pageText.isEnabled=false

        binding.prevButton.setOnClickListener {
            if (binding.pdfViewer.currentPage!=0){
                binding.pdfViewer.jumpTo(binding.pdfViewer.currentPage-1)
            }
        }

        binding.pageText.addTextChangedListener {

        }

        binding.pageText.addOnLayoutChangeListener { view, i, i2, i3, i4, i5, i6, i7, i8 ->

        }

    }

    private fun selectPdfFromStorage() {
        Toast.makeText(this, "selectPDF", Toast.LENGTH_LONG).show()
        val browseStorage = Intent(Intent.ACTION_GET_CONTENT)
        browseStorage.type = "application/pdf"
        browseStorage.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(
            Intent.createChooser(browseStorage, "Select PDF"), PDF_SELECTION_CODE
        )
    }

    companion object {
        private const val PDF_SELECTION_CODE = 99
    }
    private fun showPdfFromUri(uri: Uri?) {
        binding.pdfViewer.fromUri(uri)
            .defaultPage(0)
            .spacing(10)
            .onPageChange { page, pageCount ->
                 //Log.v("MyTag", page.toString())
                 //Log.v("MyTag", pageCount.toString())
                binding.pageText.setText(" ${page}/${pageCount}")


                binding.goPage.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
                    androidx.appcompat.widget.SearchView.OnQueryTextListener {


                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        //binding.pdfViewer.jumpTo(0)
                        return false
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        if(!p0.isNullOrEmpty()){
                            binding.pdfViewer.jumpTo(p0.toString().toInt())
                        }

                        return false
                    }

                })
             /*   binding.pdfViewer.jumpTo(binding.pageText.text.toString().toInt())*/
            }
            .load()

            /*.onPageScroll { page, positionOffset ->

                binding.pageText.setText(" ${page}/${binding.pdfViewer.pageCount}")
                //Log.v("MyTag", page.toString())
                //Log.v("MyTag", positionOffset.toString())
            }*/

/*
        binding.pdfViewer.setOnScrollChangeListener { view, i, i2, i3, i4 ->


        }*/



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PDF_SELECTION_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedPdfFromStorage = data.data
            showPdfFromUri(selectedPdfFromStorage)
        }
    }

    private fun onTapListener(){


    }
}

/*private fun PDFView.Configurator.onTap(onTapListener: Unit): PDFView.Configurator? {
    Log.v("MyTag", onTapListener.toString())
    return PDFView.Configurator(onTap {


    })
}*/

package com.fitgoalsappsmoviles.fitgoals.activities

import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fitgoalsappsmoviles.fitgoals.R
import com.squareup.picasso.Picasso

class ExerciseDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)

        val name = intent.getStringExtra("EXERCISE_NAME") ?: "Nombre no disponible"
        val imageUrl = intent.getStringExtra("EXERCISE_IMAGE_URL") ?: ""
        val series = intent.getStringExtra("EXERCISE_SERIES") ?: "Información no disponible"
        val description = intent.getStringExtra("EXERCISE_DESCRIPTION") ?: "Descripción no disponible"
        val videoUrl = intent.getStringExtra("EXERCISE_VIDEO_URL") ?: ""

        findViewById<TextView>(R.id.tvExerciseName).text = name
        findViewById<TextView>(R.id.tvExerciseDescription).text = description
        findViewById<TextView>(R.id.tvExerciseSeries).text = series

        val webView = findViewById<WebView>(R.id.webViewExerciseVideo)
        webView.settings.javaScriptEnabled = true
        webView.settings.mediaPlaybackRequiresUserGesture = false // Permite la reproducción automática del vídeo

        // Configuración de la URL para embeber el vídeo
        val embedUrl = "https://www.youtube.com/embed/${extractVideoId(videoUrl)}"
        val html = """
            <html>
            <body>
            <iframe width="100%" height="100%" style="border-radius: 20px;"  src="$embedUrl" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
            </body>
            </html>
        """.trimIndent()

        webView.loadData(html, "text/html", "utf-8")
    }

    // Función auxiliar para extraer el ID del vídeo de la URL completa
    private fun extractVideoId(url: String): String {
        return url.substringAfter("watch?v=").substringBefore("&")
    }
}

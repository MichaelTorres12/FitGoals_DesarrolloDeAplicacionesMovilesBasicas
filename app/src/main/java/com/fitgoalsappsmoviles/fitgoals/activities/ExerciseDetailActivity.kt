package com.fitgoalsappsmoviles.fitgoals.activities

//Activity que muestra los detalles y el video de cada ejercicio en especifico para qu el usuario se guie

import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fitgoalsappsmoviles.fitgoals.R
import com.squareup.picasso.Picasso

// Activity que muestra detalles específicos de un ejercicio seleccionado, incluyendo un video.
class ExerciseDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)

        // Recupera datos del ejercicio desde el intent que inició esta actividad (ExerciseListActivity).
        val name = intent.getStringExtra("EXERCISE_NAME") ?: "Nombre no disponible"
        val imageUrl = intent.getStringExtra("EXERCISE_IMAGE_URL") ?: ""
        val series = intent.getStringExtra("EXERCISE_SERIES") ?: "Información no disponible"
        val description = intent.getStringExtra("EXERCISE_DESCRIPTION") ?: "Descripción no disponible"
        val videoUrl = intent.getStringExtra("EXERCISE_VIDEO_URL") ?: ""

        // Establece los datos recuperados en los elementos de la vista.
        findViewById<TextView>(R.id.tvExerciseName).text = name
        findViewById<TextView>(R.id.tvExerciseDescription).text = description
        findViewById<TextView>(R.id.tvExerciseSeries).text = series

        // Configura WebView para mostrar el video del ejercicio.
        val webView = findViewById<WebView>(R.id.webViewExerciseVideo)
        webView.settings.javaScriptEnabled = true  // Habilita JavaScript para la reproducción del video.
        webView.settings.mediaPlaybackRequiresUserGesture = false  // Permite la reproducción automática sin gestos del usuario.

        // Prepara el código HTML para embeber el video de YouTube usando el ID del video extraído.
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

    // Extrae el ID del video de una URL de YouTube para embeber en el WebView.
    private fun extractVideoId(url: String): String {
        return url.substringAfter("watch?v=").substringBefore("&")
    }
}

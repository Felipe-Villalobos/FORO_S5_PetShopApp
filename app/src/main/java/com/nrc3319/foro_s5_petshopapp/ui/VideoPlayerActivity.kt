// ruta: app/src/main/java/com/nrc3319/foro_s5_petshopapp/ui/VideoPlayerActivity.kt
package com.nrc3319.foro_s5_petshopapp.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.nrc3319.foro_s5_petshopapp.R

class VideoPlayerActivity : AppCompatActivity() {

    private var exoPlayer: ExoPlayer? = null
    private lateinit var playerView: PlayerView
    private var videoUrl: String? = null

    // Variables para guardar el estado del reproductor
    private var playWhenReady = true
    private var playbackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        // Obtenemos la URL del intent TAN PRONTO como la actividad se crea.
        videoUrl = intent.getStringExtra("VIDEO_URL")
        playerView = findViewById(R.id.videoPlayerView)

        // Ocultar la barra de sistema para una experiencia inmersiva
        hideSystemUi()
    }

    // Usamos onResume para inicializar el reproductor.
    // Se llama cuando la actividad está a punto de empezar a interactuar con el usuario.
    public override fun onResume() {
        super.onResume()
        if (exoPlayer == null) {
            initializePlayer()
        }
    }

    // Usamos onPause para liberar el reproductor.
    // Se llama cuando el sistema está a punto de reanudar otra actividad.
    public override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    private fun initializePlayer() {
        if (videoUrl == null) {
            // Si no hay URL, no hay nada que hacer.
            return
        }

        // Construimos el reproductor
        exoPlayer = ExoPlayer.Builder(this).build().also { player ->
            playerView.player = player
            val mediaItem = MediaItem.fromUri(videoUrl!!)
            player.setMediaItem(mediaItem)

            // Restauramos el estado
            player.playWhenReady = playWhenReady
            player.seekTo(playbackPosition)

            // Preparamos el reproductor para la reproducción
            player.prepare()
        }
    }

    private fun releasePlayer() {
        exoPlayer?.let { player ->
            // Guardamos el estado actual antes de liberar
            playbackPosition = player.currentPosition
            playWhenReady = player.playWhenReady

            player.release()
            exoPlayer = null
        }
    }

    private fun hideSystemUi() {
        // Esta función hace que el video ocupe toda la pantalla para una mejor experiencia.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }
}

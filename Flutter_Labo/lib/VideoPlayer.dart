import 'dart:async';
import 'dart:io';
// import 'package:camera/camera.dart';
import 'package:flutter/material.dart';
import 'package:video_player/video_player.dart';
class VideoPlayerApp extends StatefulWidget {
  File file;

  VideoPlayerApp({Key? key, required this.file}) : super(key: key);

  @override
  _VideoPlayerScreenState createState() => _VideoPlayerScreenState(file);
}

class _VideoPlayerScreenState extends State<VideoPlayerApp> {
  late VideoPlayerController _controller;
  late Future<void> _initializeVideoPlayerFuture;
  File file;

  _VideoPlayerScreenState(this.file);

  @override
  void initState() {
    super.initState();
    // Initialize and store for later use.
    _controller = VideoPlayerController.file(file);
    _initializeVideoPlayerFuture = _controller.initialize();
    // The video will automatically start again when done
    _controller.setLooping(true);
  }

  @override
  void dispose() {
    // Ensure disposing of the VideoPlayerController to free up resources.
    _controller.dispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar:AppBar(title: const Text("Vidéo"),),
        // Use a FutureBuilder to display a loading spinner while waiting for the
        // VideoPlayerController to finish initializing.
        body: FutureBuilder(
          future: _initializeVideoPlayerFuture,
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.done) {
              // If the VideoPlayerController has finished initialization, use
              // the data it provides to limit the aspect ratio of the video.
              return AspectRatio(
                aspectRatio: _controller.value.aspectRatio,
                // This will display the video.
                child: VideoPlayer(_controller),
              );
            } else {
              // If the VideoPlayerController is still initializing, show a loading spinner.
              return const Center(
                child: CircularProgressIndicator(),
              );
            }
          },
        ),
        floatingActionButton: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () {
                setState(() {
                  // _controller.value.isPlaying
                  //     ? _controller.pause()
                  //     : _controller.play();
                  Navigator.pop(context);
                });
              },
              child: const Text("Arrière", style: TextStyle(fontSize: 20)),
            ),
            const SizedBox(width: 80),
            ElevatedButton(
              onPressed: () {
                setState(() {
                  _controller.value.isPlaying
                      ? _controller.pause()
                      : _controller.play();
                });
              },
              child: _controller.value.isPlaying
                  ? const Text("Pause", style: TextStyle(fontSize: 20))
                  : const Text("Play", style: TextStyle(fontSize: 20)),
            )
          ],
        ),
      ),
    );
  }
}
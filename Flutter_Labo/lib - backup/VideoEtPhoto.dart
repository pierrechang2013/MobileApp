import 'dart:async';
import 'dart:io';
import 'package:camera/camera.dart';
import 'package:flutter/material.dart';
import 'VideoPlayer.dart' as vp;
import 'DisplayPictureScreen.dart' as dps;
class TakePictureScreen extends StatefulWidget {
  const TakePictureScreen({
    Key? key,
    required this.camera,
  }) : super(key: key);
  final CameraDescription camera;
  @override
  TakePictureScreenState createState() => TakePictureScreenState();
}
class TakePictureScreenState extends State<TakePictureScreen> {
  late CameraController _controller;
  late Future<void> _initializeControllerFuture;
  bool _isRecording = false;
  @override
  void initState() {
    super.initState();
    _controller = CameraController(widget.camera, ResolutionPreset.medium,);
    _initializeControllerFuture = _controller.initialize();
  }
  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Photo et Vidéo  ')),
      body: FutureBuilder<void>(
        future: _initializeControllerFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.done) {
            return CameraPreview(_controller);
          } else {
            return const Center(child: CircularProgressIndicator());
          }
        },
      ),
      floatingActionButton: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          ElevatedButton(
            onPressed: () async {
              await _recordVideo();
            },
            child: _isRecording
                ? const Text("Arrêter", style: TextStyle(fontSize: 20))
                : const Text("vidéo", style: TextStyle(fontSize: 20),
                  ),
          ),
          const SizedBox(width: 80),
          ElevatedButton(
            onPressed: () async {
              await _takePicture();
            },
            child: const Text("Photo", style: TextStyle(fontSize: 20),
            ),
          ),
        ],
      ),
    );
  }
  _takePicture() async {
    try {
      await _initializeControllerFuture;
      final image = await _controller.takePicture();
      await Navigator.of(context).push(
        MaterialPageRoute(
          builder: (context) => dps.DisplayPictureScreen(
            imagePath: image.path,
          ),
        ),
      );
    } catch (e) {
      //print("错误"+e.toString());
    }
  }
  _recordVideo() async {
    if (_isRecording) {
      XFile? xFile = await _controller.stopVideoRecording();
      setState(() => _isRecording = false);
      final route = MaterialPageRoute(
        fullscreenDialog: true,
        builder: (_) => vp.VideoPlayerApp(file: File(xFile!.path)),
      );
      Navigator.push(context, route);
    } else {
      await _controller.prepareForVideoRecording();
      await _controller.startVideoRecording();
      setState(() => _isRecording = true);
    }
  }
}


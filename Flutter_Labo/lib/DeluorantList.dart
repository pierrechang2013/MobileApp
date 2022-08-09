import 'package:flutter/material.dart';
import 'SQlit.dart' as sq;
import 'package:sqflite/sqflite.dart';
import 'package:video_player/video_player.dart';
import 'package:camera/camera.dart';
import 'package:tp3/VideoEtPhoto.dart' as vp;

class DeluorantList extends StatefulWidget {
  const DeluorantList({Key? key}) : super(key: key);

  @override
  _DeluorantListState createState() => _DeluorantListState();
}
class _DeluorantListState extends State<DeluorantList> {
  var _animalList = <sq.Animal>[];
  var db;
  var tabs = <Tab>[];
  var cameras;
  List<sq.Animal> getData()  {
    sq.getDb().then((value){

        sq.getAllData1(value).then((value) {
          setState(() {//必须有这个，否则就赋值就不成功
            _animalList = value;
          });

        }
        );
    });
    return _animalList;
  }
  @override
  void initState() {
    super.initState();
    tabs = const [
      Tab(icon: Icon(Icons.pets)),
      Tab(icon: Icon(Icons.accessibility_new)),
    ];
    sq.getDb().then((value){db = value;setState(() {
    });}
    );
    getData();
    availableCameras().then((value) => cameras = value);
  }
  Widget build(BuildContext context) {

    return DefaultTabController(
      length: tabs.length,
      child: Scaffold(
        appBar: AppBar(
            title: const Text('Lists deroulantes'),
            bottom: TabBar(
              tabs: tabs,
            )),
        body: TabBarView(children: [
          _DataList(),
          _DataList(),
        ]),
        floatingActionButton: FloatingActionButton(
          child: const Icon(Icons.exit_to_app),
          onPressed: () async {
            Navigator.push(context, MaterialPageRoute(builder: (context) {
              // Get a specific camera from the list of available cameras.
              final firstCamera = cameras.first;
              return vp.TakePictureScreen(camera: firstCamera);
            }));
          },
        ),
      ),
    );
  }
  Widget _DataList() {

    return ListView.builder(
        itemCount: _animalList.length,
        padding: const EdgeInsets.all(16.0),
        itemBuilder: (context, i) {
          return _buildRow(_animalList[i],i);
        });
  }
  Widget _buildRow(sq.Animal animal,int i) {
    return ListTile(
      title: Text(
        animal.toString(),
        style: const TextStyle(fontSize: 20),
      ),
      leading: IconButton(
        icon: const Icon(Icons.delete),
        onPressed: () {
          _animalList.remove(animal);

          setState(() {sq.deleteAnimal(animal.id
              , db).then((value) => print("delete id:——————————$value"));});
        },
      ),
    );
  }
}

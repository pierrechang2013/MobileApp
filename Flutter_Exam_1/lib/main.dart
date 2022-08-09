import 'package:flutter/material.dart';

void main() => runApp(Exam1());

class Exam1 extends StatelessWidget {
  const Exam1({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Exam1',
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Exam1'),
        ),
        body: const HomePageWidget(),
        drawer: Drawer(
    child: ListView(
      padding: EdgeInsets.zero,
      children: [
        const DrawerHeader(
          decoration: BoxDecoration(
            color: Colors.blue,
          ),
          child: Text('Drawer Header'),
        ),
          ListTile(
                    title: const Text('Switch'),
                    //contentPadding: EdgeInsets.zero,
                    onTap: () {
                    Navigator.pop(context);
                    },
                    trailing:SwitchStatefulWidget(),//ListTile里不能用Switchlist组件否则会报布局错误
                ),


      ]
    )
        ),
      ),

    );
  }
}

class SwitchStatefulWidget extends StatefulWidget{

  SwitchStatefulWidget({Key? key}):super(key: key){
  }
  @override
  State<SwitchStatefulWidget> createState()=> _SwitchWidgetState();

}

class HomePageWidget extends StatefulWidget {

  const HomePageWidget({Key? key}) : super(key: key);

  @override
  State<HomePageWidget> createState()//必须实现这个createState()方法，返回值就是一个State<T extends statefulWidget>对象
  => _HomePageStatefulWidgetState();

}

class _HomePageStatefulWidgetState extends State<HomePageWidget> {
  late TextEditingController _controller;

  @override
  void initState() {//初始化方法
    super.initState();
    _controller = TextEditingController();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    //State<T extends StatefulWidget> 是抽象类，build(BuildContext bc)是没有实现体的，所以必须实现
    return Scaffold(
      body: Column(
        children: [TextField(
          controller: _controller,

        ),ElevatedButton(

          onPressed: () {
            ScaffoldMessenger.of(context).showSnackBar(
                SnackBar(
                  content: Text(_controller.text),
                  backgroundColor: Colors.black45,

                  action: SnackBarAction(
                    label: 'Undo',
                    onPressed: () {
                      // Some code to undo the change.
                      //print("Ici SnackBarAction");
                    },
                  ),
                )
            );
          },
          child: const Text('Show SnackBar'),
        ),]
      ),
    );
  }
}

class _SwitchWidgetState extends State<SwitchStatefulWidget>{
  bool _click = false;

  Widget build(BuildContext  buildContext){



        return SizedBox(
          child: Switch(
              //title:const Text("Switch"),
                value:_click,
                onChanged:(value){
                  setState(() {
                    _click = value;
                  });
                }


    ),
        );
  }
}

class MainPage extends StatelessWidget {
  const MainPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    return Center(

      child: ElevatedButton(

        onPressed: () {//

          final snackBar = SnackBar(

            content: const Text('Sortiez le SnackBar,svp!'),
            action: SnackBarAction(
              label: 'Undo',
              onPressed: () {
                // Some code to undo the change.

              },
            ),
          );

          ScaffoldMessenger.of(context).showSnackBar(snackBar);
        },
        child: const Text('Show SnackBar'),
      ),
    );
  }
}


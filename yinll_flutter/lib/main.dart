import 'package:flutter/material.dart';
import 'package:yinll_flutter/CustomView/DemoPage.dart';
import 'package:yinll_flutter/keyboard/keyboard_main.dart';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Flutter Demo',
      theme: new ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: new MyHomePage(title: 'Demo'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => new _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  void _incrementCounter() {
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text(widget.title),
      ),
      body: new Column(
        children: <Widget>[
          new Padding(
            padding: const EdgeInsets.only(left: 20.0, top: 20.0),
            child: new FlatButton(
                color: Colors.greenAccent,
                onPressed: () {
                  Navigator.push(
                      context,
                      new MaterialPageRoute(
                          builder: (context) => new DemoPage()));
                },
                child: new Text('自定义饼状图')),
          ),

          new Padding(
            padding: const EdgeInsets.only(left: 20.0, top: 20.0),
            child: new FlatButton(
                color: Colors.greenAccent,
                onPressed: () {
                  Navigator.push(
                      context,
                      new MaterialPageRoute(
                          builder: (context) => new main_keyboard()));
                },
                child: new Text('支付密码+安全键盘')),
          ),

        ],
      ),
    );
  }
}

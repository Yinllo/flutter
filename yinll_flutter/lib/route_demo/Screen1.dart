import 'package:flutter/material.dart';
import 'package:yinll_flutter/route_demo/Screen2.dart';
import 'package:yinll_flutter/route_demo/Screen3.dart';
import 'package:yinll_flutter/route_demo/Screen4.dart';


void main() {
  runApp(
      new MaterialApp(
        home: new Screen1(),
        routes: <String, WidgetBuilder> {
          '/screen1': (BuildContext context) => new Screen1(),
          '/screen2' : (BuildContext context) => new Screen2(),
          '/screen3' : (BuildContext context) => new Screen3(),
          '/screen4' : (BuildContext context) => new Screen4()
        },
      )
  );
}

class Screen1 extends StatelessWidget {


  @override
  Widget build(BuildContext context) {
    print("Screen1");

    return new Scaffold(
      appBar: new AppBar(
        title: new Text("Screen 1"),

      ),
      body: new Center(
        child: new Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            new RaisedButton(onPressed:(){
              Navigator.of(context).pushNamed('/screen2');
            } ,child: new Text("Push to Screen 2"),),
            new SizedBox(height: 10.0,),

            new RaisedButton(
              onPressed: (){
                print(Navigator.of(context).canPop().toString());
              },
              child: new Text("Can Pop"),),
            new SizedBox(height: 10.0,),

            new RaisedButton(
              onPressed: (){
                Navigator.of(context).maybePop();
              },
              child: new Text("Maybe Pop"),),
            new SizedBox(height: 10.0,),

            new RaisedButton(
              onPressed: (){
                Navigator.of(context).pop();
              },
              child: new Text("Pop"),)
          ],
        ),
      ) ,
    );
  }
}
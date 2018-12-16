import 'package:flutter/material.dart';
import 'package:yinll_flutter/route_demo/Screen5.dart';

class Screen2 extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    print("Screen2");


    return new Scaffold(
      appBar: new AppBar(
        title: new Text("Screen 2"),
        automaticallyImplyLeading: true,
      ),
      body: new Center(
        child: new Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            new RaisedButton(onPressed: () {
              Navigator.of(context).pushNamed('/screen3');
            },
              child: new Text("Push to Screen 3"),),
            new SizedBox(height: 10.0,),


            new RaisedButton(onPressed:(){
              Navigator.of(context).pushNamed('/screen1');
            } ,child: new Text("Push to Screen 1 instead of Pop"),),
            new SizedBox(height: 10.0,),

            new RaisedButton(onPressed: () {
              String userName = "John Doe";
              Navigator.push(
                  context,
                  new MaterialPageRoute(
                      builder: (BuildContext context) =>
                      new Screen5(userName)));
            },
              child: new Text("Push to Screen 5 using MaterialPageRoute"),),

          ],
        ),
      ) ,
    );

  }
}
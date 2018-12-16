import 'package:flutter/material.dart';


class Screen5 extends StatelessWidget {

  final String userName;
  Screen5(this.userName);
  @override
  Widget build(BuildContext context) {

    print("Screen5");


    return new Scaffold(
      appBar: new AppBar(
        title: new Text("Screen 5"),
        automaticallyImplyLeading: true,
      ),
      body: new Center(
        child: new Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            new Text("Hi " + userName),
          ],
        ),
      ) ,
    );

  }
}
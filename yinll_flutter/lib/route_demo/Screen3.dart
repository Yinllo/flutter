import 'package:flutter/material.dart';
import 'package:yinll_flutter/route_demo/Screen4.dart';

class Screen3 extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    print("Screen3");

    return new Scaffold(
      appBar: new AppBar(
        title: new Text("Screen 3"),

      ),
      body: new Center(
        child: new Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
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
                Navigator.of(context).pushReplacementNamed('/screen4');
              },
              child: new Text("Push Replacement Named"),),
            new SizedBox(height: 10.0,),


            new RaisedButton(
              onPressed: (){
                Navigator.popAndPushNamed(context, '/screen4');
              },
              child: new Text("pop and Push Named"),),
            new SizedBox(height: 10.0,),


            new RaisedButton(
              onPressed: (){
                Navigator.of(context).pushNamedAndRemoveUntil('/screen4', ModalRoute.withName('/screen1'));
                // Navigator.of(context).pushNamedAndRemoveUntil('/screen4', (Route<dynamic> route) => false);
//                Navigator.pushNamedAndRemoveUntil(context, "/screen4",ModalRoute.withName('/screen1'));
              },
              child: new Text("Push Named and Remove Until"),),
            new SizedBox(height: 10.0,),


            new RaisedButton(
              onPressed: (){
                Navigator.of(context).pushAndRemoveUntil(new MaterialPageRoute( builder: (BuildContext context) => new Screen4()), ModalRoute.withName('/screen1'));
              },
              child: new Text("Push and Remove Until"),),
            new SizedBox(height: 10.0,),





            new RaisedButton(onPressed:(){
              Navigator.of(context).pushNamed('/screen4');
            } ,child: new Text("Push to Screen 4"),),
            new SizedBox(height: 10.0,),


            new RaisedButton(
              onPressed: (){
                Navigator.push(context, new PageRouteBuilder(
                    opaque: false,
                    pageBuilder: (BuildContext context, _, __) {
                      return new Scaffold(
                        body: new Center(
                          child: new Container(
                              child: new Center(child: new Text('My PageRoute'))),
                        ),
                      );
                    },
                    transitionsBuilder: (___, Animation<double> animation, ____, Widget child) {
                      return new FadeTransition(
                        opacity: animation,
                        child: new RotationTransition(
                          turns: new Tween<double>(begin: 0.5, end: 1.0).animate(animation),
                          child: child,
                        ),
                      );
                    }
                ));
              },
              child: new Text("Page Route Builder"),),


          ],
        ),
      ) ,
    );

  }
}
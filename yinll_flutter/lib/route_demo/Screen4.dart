import 'package:flutter/material.dart';


class Screen4 extends StatelessWidget {


  @override
  Widget build(BuildContext context) {
    print("Screen4");

    return new Scaffold( // 1
      appBar: new AppBar( //2
        title: new Text("Screen 4"),

      ),
      body: new Center( // 3
        child: new Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            new RaisedButton(
              onPressed: (){
                Navigator.popUntil(context, ModalRoute.withName('/screen2'));
              },
              child: new Text("popUntil"),),
            new SizedBox(height: 10.0,),

            new RaisedButton(onPressed: ()async{
              String value = await Navigator.push(context, new MaterialPageRoute(
                  builder: (BuildContext context) {
                    return new Center(
                      child: new GestureDetector(
                          child: new Text('OK'),
                          onTap: () { Navigator.pop(context, "Audio1"); }
                      ),
                    );
                  }
              )
              );
              print(value);

            },
              child: new Text("Return"),)

          ],
        ),
      ) ,
    );
  }
}

import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:yinll_flutter/redux/AppState.dart';
import 'package:yinll_flutter/redux/user.dart';
import 'package:yinll_flutter/redux/user_reducer.dart';

class Demo extends StatefulWidget{

  static final String sName = "demo";

  @override
  State<StatefulWidget> createState() {
    return new DemoState();
  }

}

class DemoState extends State<Demo>{


  User user = User.empty();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    get();
  }

  @override
  Widget build(BuildContext context) {

    ///通过 StoreConnector 关联 AppState 中的 User
    return new StoreConnector<AppState, User>(
      ///通过 converter 将 AppState 中的 userInfo返回
      converter: (store) => store.state.userInfo,
      ///在 userInfo 中返回实际渲染的控件
      builder: (context, userInfo) {
        return new Scaffold(
          appBar: new AppBar(
            title: new Text('ooooo'),
          ),
          body: new Column(
            children: <Widget>[
              new Text(
                userInfo.phone==null ? "aaa" : userInfo.name,
                style: Theme.of(context).textTheme.display1,
              ),

              new Padding(padding: const EdgeInsets.only(top: 50.0)),

              new GestureDetector(
                child: new Text('dianji'),
                onTap: (){
                  StoreConnector<AppState,VoidCallback>(
                      converter: (store) {
                        return () => store.dispatch(new UpdateUserAction(user));
                      },
                      builder: (context, callback)
                      {
                        return FloatingActionButton(
                          onPressed: callback,
                          child: Icon
                            (Icons.add),
                        );

                      });
                },
              )

            ],
          )
        );
      },
    );

  }

  void get(){

    setState(() {

      user.name='1111';
      user.code='1111';
      user.photo='1111';
      user.phone='1111';
      ///通过 dispatch UpdateUserAction，可以更新State
      StoreProvider.of(context).dispatch(new UpdateUserAction(user));
    });
  }


}
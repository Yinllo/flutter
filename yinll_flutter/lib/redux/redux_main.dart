import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:redux/redux.dart';
import 'package:yinll_flutter/redux/AppState.dart';
import 'package:yinll_flutter/redux/DemoUseStorePage.dart';
import 'package:yinll_flutter/redux/demo.dart';
import 'package:yinll_flutter/redux/user.dart';

void main() {
  runApp(new FlutterReduxApp());
}

class FlutterReduxApp extends StatelessWidget {
  /// 创建Store，引用 AppState 中的 appReducer 创建 Reducer
  /// initialState 初始化 State
  final store = new Store<AppState>(
    appReducer,
    initialState: new AppState(
        userInfo: User.empty(),
    )
  );

  FlutterReduxApp({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    /// 通过 StoreProvider 应用 store
    return new StoreProvider(
      store: store,
      child: new StoreBuilder<AppState>(builder: (context, store) {
      return new MaterialApp(
          theme: new ThemeData(
            primarySwatch: Colors.blue,
          ),
            home: new Demo(),
//          home: DemoUseStorePage(),

        );
      }),
    );
  }
}

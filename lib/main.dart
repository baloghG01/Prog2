
import 'package:app2/screens/auth/register.dart';
import 'package:app2/screens/auth/sing%20in.dart';
import 'package:app2/screens/wrapper.dart';
import 'package:app2/szolgaltatasok/authser.dart';
import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:provider/provider.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MultiProvider(providers: [
      Provider<AuthService>(create: (_) => AuthService(), ),

    ],
      child: MaterialApp(
        initialRoute: '/',
            routes: {
          '/' : (context) => Wrapper(),
           '/register': (context) => Register(),
           '/singin': (context) => SingIn()



      },
      ),
    );
  }
}


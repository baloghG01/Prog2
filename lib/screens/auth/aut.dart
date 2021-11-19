import 'package:app2/screens/auth/register.dart';
import 'package:app2/screens/auth/sing%20in.dart';
import 'package:flutter/material.dart';

class Auth extends StatefulWidget {
  @override
  _AuthState createState() => _AuthState();
}

class _AuthState extends State<Auth> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: SingIn(),
    );
  }
}

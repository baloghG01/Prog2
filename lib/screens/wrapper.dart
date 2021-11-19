import 'package:app2/models/users.dart';
import 'package:app2/screens/auth/aut.dart';
import 'package:app2/screens/auth/sing%20in.dart';
import 'package:app2/screens/home/home.dart';
import 'package:app2/szolgaltatasok/authser.dart';
import 'package:firebase_auth/firebase_auth.dart' as auth;
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class  Wrapper extends StatelessWidget {
  @override
  Widget  build(BuildContext context) {
    final authservice = Provider.of<AuthService>(context);
    return StreamBuilder<User?> (
      stream: authservice.user,
        builder: (_, AsyncSnapshot<User?>snapshot){
        if(snapshot.connectionState == ConnectionState.active){
          final User? user = snapshot.data;
          return user == null? SingIn() : Home();
        }else{
          return Scaffold(
            body: Center(
              child: CircularProgressIndicator()
            )
          );
        }

    }
    );


    //vagy a home vagy a auth screent adja vissza
  }
}

import 'package:app2/szolgaltatasok/authser.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';


class Home extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    final authService = Provider.of<AuthService> (context);
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        elevation: 0.0,
        actions: <Widget>[
          TextButton.icon(onPressed: () async {
            await authService.singOut();

          },
              icon: Icon(Icons.person, color: Colors.black,),
              label: Text("Log out",
              style: TextStyle(
                color: Colors.black
              ),))
        ],
      ),

    );
  }
}

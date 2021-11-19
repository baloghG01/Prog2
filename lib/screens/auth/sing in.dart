import 'package:app2/screens/auth/register.dart';
import 'package:app2/szolgaltatasok/authser.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class SingIn extends StatefulWidget {


  @override
  _SingInState createState() => _SingInState();
}

class _SingInState extends State<SingIn> {

  final AuthService _auth = AuthService();


  @override
  Widget build(BuildContext context) {
    final TextEditingController emailController = TextEditingController();
    final TextEditingController passwordController = TextEditingController();

    final authservice = Provider.of<AuthService> (context);


    return Scaffold(
      body: SingleChildScrollView(
        child: Column(
          children: [
            Container(
              height: 300,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.only(bottomLeft: Radius.circular(90)),
                gradient: LinearGradient(
                  colors: [new Color(0xFFBA68C8), new Color(0xFF9C27B0)],
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter
                )
              ),
              child: Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    SizedBox(height: 30,),
                    Text("Welcome",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 50,
                      fontWeight: FontWeight.bold
                    ),
                    ),
                    Container(
                      margin: EdgeInsets.only(right: 20),
                      alignment: Alignment.bottomRight,
                      child: Text("Login",
                      style: TextStyle(
                        fontSize: 15,
                        color: Colors.white,
                      ),),
                    )
                  ],
                ),
              ),
            ),
           Form(child: Column(
             children: <Widget> [
               SizedBox(height: 50.0,),
               Container(
                 margin: EdgeInsets.only(left: 20, right: 20),
                 height: 50,
                 decoration: BoxDecoration(
                   borderRadius: BorderRadius.circular(45),
                   color: Colors.purple[100],
                 ),
                 child: Center(
                   child: TextFormField(
                     controller: emailController,
                       decoration: InputDecoration(hintText: "Email",
                           border: InputBorder.none,
                           icon: Padding(
                             padding: const EdgeInsets.only(left: 8),
                             child: Icon(
                               Icons.email,
                             ),
                           )
                       ),
                       obscureText: false,
                   ),
                 ),
               ),

               SizedBox(height: 20.0,),
               Container(
                 margin: EdgeInsets.only(left: 20, right: 20),
                 height: 50,
                 decoration: BoxDecoration(
                   borderRadius: BorderRadius.circular(45),
                   color: Colors.purple[100],
                 ),
                 child: Center(
                   child: TextFormField(
                     controller: passwordController,
                     decoration: InputDecoration(hintText: "Password",
                     border: InputBorder.none,
                       icon: Padding(
                         padding: const EdgeInsets.only(left: 8),
                         child: Icon(
                           Icons.vpn_key,
                         ),
                       )
                     ),
                       obscureText: true
                   ),
                 ),
               )

             ]

           )
           ),

            SizedBox(height: 80,),

            Container(
              margin: EdgeInsets.only(left: 20,right: 20),
              height: 60,
              width: 250,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(45),
                color: Colors.purple,
              ),
              child: MaterialButton(
                child: Text("Login",
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: 20,
                  ),
                ),
                  onPressed: () async {
                  authservice.singinWithEmailandPassword(
                      emailController.text,
                      passwordController.text);
                  }
                  ),
            ),
            Container(
              margin: EdgeInsets.only(top: 8),
              alignment: Alignment.bottomCenter,
              child: Text("Don't have an accaunt?"),
            ),
            Container(
              margin: EdgeInsets.only(right: 10),
              alignment: Alignment.bottomRight,
              child: MaterialButton(
                child: Text("Sing Up!",
                style: TextStyle(
                  fontWeight: FontWeight.bold
                ),),
                onPressed: () {
                  Navigator.push(context, MaterialPageRoute(builder: (context) => Register()));
                },
              ),
            )

          ],
        ),
      )
    );
  }
}



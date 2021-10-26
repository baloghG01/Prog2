import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'login.dart';
import 'sinup.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:google_fonts/google_fonts.dart';

void main() {
  runApp(MaterialApp(
    debugShowCheckedModeBanner: false,
    home: WelcomePage(),
  ));
}

class WelcomePage extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.black,
        child: Stack(
          children: [ Positioned.fill(
            child: Opacity(
              opacity: 0.8,
              child:
              Image.asset('images/hatter.jpg',
             fit: BoxFit.cover,),
            ),

          ),
            Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  Center(
                    child: ClipOval(
                      child: Container(
                        width: 180,
                        height: 180,
                        color: Colors.white,
                        child: Image.asset('images/logo3.png',
                            fit: BoxFit.cover),
                      ),
                    ),

                  ),
                  Text("Blue Heart",
                    textAlign: TextAlign.center,
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 40,
                      fontWeight: FontWeight.bold
                    ),
                  ),
                  SizedBox(height: 25),
                  Text("Best place to start",
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: 35,
                    //fontWeight: FontWeight.bold
                  ),
                  ),
                  Text("Find the perfect match",
                    textAlign: TextAlign.center,
                    style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                    ),
                  ),
                  SizedBox(height: 150),
                  Padding(
                    padding: const EdgeInsets.all(20),
                    child: MaterialButton( onPressed: () {
                      Navigator.push(context,
                          MaterialPageRoute(builder: (context) => LogInPage()
                      )
                      );

                    },
                        padding: EdgeInsets.all(20),
                        color: Colors.white,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(50)
                        ),
                        child: Container(
                        padding: EdgeInsets.all(5),
                          child: Text("Login",
                            style: TextStyle(
                                color: Colors.blue,
                                fontSize: 23,
                                fontWeight: FontWeight.bold
                            ),),
                        )

                    ),
                  ),
                  Container(
                    margin: EdgeInsets.only(left: 20, right: 20),
                    child: Material(
                      color: Colors.transparent,
                      child: InkWell(
                        borderRadius: BorderRadius.circular(50),
                        onTap: () {
                          Navigator.push(context,
                          MaterialPageRoute(builder: (context) => SingUpPage()
                          )
                          );
                        },
                        child: Container(
                            padding: EdgeInsets.all(20),
                            child: Text("Sing Up",
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                  color: Colors.white,
                                  fontSize: 23,
                                  fontWeight: FontWeight.bold
                              ),
                            ),
                            decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(50),
                              color: Colors.transparent,
                              border: Border.all(
                                color: Colors.white,
                                width: 4,
                              ),
                            ),
                        ),

                      ),
                    ),
                  ),
                ],
              ),
            )
      ],
        ),

      ),
    );
  }
  }

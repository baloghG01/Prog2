import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:google_fonts/google_fonts.dart';


class LogInPage extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        Container(
          decoration: BoxDecoration(image: DecorationImage(
            image: AssetImage('images/hatter.jpg',),
            fit: BoxFit.cover
          )),
        ),
        Scaffold(
          backgroundColor: Colors.transparent,
          body: Column(
            children: [
              Flexible(child: Center(
                child: Text("Blue Heart",
                style: TextStyle(
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                  fontSize: 50

                ),
                ),
              ),
              ),
              Padding(
                padding: const EdgeInsets.all(20),
                child: Container(
                  height: 70,
                  decoration: BoxDecoration(
                    color: Colors.grey[500].withOpacity(0.8),
                    borderRadius: BorderRadius.circular(15)
                  ),
                  child: Center(
                    child: TextField(
                      decoration: InputDecoration(
                        prefixIcon: Icon(FontAwesomeIcons.envelope, size: 30, color: Colors.white,),
                        border: InputBorder.none,
                        hintText: 'Email',
                        hintStyle: TextStyle(
                          color: Colors.white,
                          fontSize: 20,
                        )
                      ),
                    ),
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(left: 20, right: 20, bottom: 20),
                child: Container(
                  height: 70,
                  decoration: BoxDecoration(
                      color: Colors.grey[500].withOpacity(0.8),
                      borderRadius: BorderRadius.circular(15)
                  ),
                  child: Center(
                    child: TextField(
                      decoration: InputDecoration(
                          prefixIcon: Icon(FontAwesomeIcons.lock, size: 30, color: Colors.white,),
                          border: InputBorder.none,
                          hintText: 'Password',
                          hintStyle: TextStyle(
                            color: Colors.white,
                            fontSize: 20,
                          )
                      ),
                    ),
                  ),
                ),
              ),
                 Column(
                   crossAxisAlignment: CrossAxisAlignment.stretch,
                   children: [
                       Padding(
                         padding: const EdgeInsets.all(34),
                         child: MaterialButton(onPressed: () {},
                           height: 50,
                          color: Colors.white,
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(15)
                          ),
                          child: Text("Log In",
                          style: TextStyle(
                            fontSize: 23
                          ),),

                      ),
                       ),
                   ],
                 ),
            ],

          ),

        ),
      ],

    );
  }
}
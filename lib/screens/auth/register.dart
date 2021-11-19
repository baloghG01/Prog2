

import 'package:app2/screens/auth/sing%20in.dart';
import 'package:app2/screens/home/home.dart';
import 'package:app2/szolgaltatasok/authser.dart';
import 'package:flutter/material.dart';
import 'package:flutter_pw_validator/flutter_pw_validator.dart';
import 'package:form_field_validator/form_field_validator.dart';
import 'package:provider/provider.dart';



class Register extends StatefulWidget {


  @override
  _RegisterState createState() => _RegisterState();
}

class _RegisterState extends State<Register> {
  String email = 'asd';
  String pass = '';

  @override
  Widget build(BuildContext context) {

    final TextEditingController emailcontrol = TextEditingController();
    final TextEditingController passwordcontrol = TextEditingController();
    final GlobalKey _key = GlobalKey<FormState>();

    final authService = Provider.of<AuthService> (context);


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
                      Text("Watch's up?",
                        style: TextStyle(
                            color: Colors.white,
                            fontSize: 50,
                            fontWeight: FontWeight.bold
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.only(right: 20),
                        alignment: Alignment.bottomRight,
                        child: Text("Sing Up",
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
                            decoration: InputDecoration(hintText: "Name",
                                border: InputBorder.none,
                                icon: Padding(
                                  padding: const EdgeInsets.only(left: 8),
                                  child: Icon(
                                    Icons.person,
                                  ),
                                )
                            ),
                            obscureText: false,
                        ),
                      ),

                    ),
                    SizedBox(height: 20,),
                    Container(
                      margin: EdgeInsets.only(left: 20, right: 20),
                      height: 50,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(45),
                        color: Colors.purple[100],
                      ),
                      child: Center(
                        child: Form(
                          autovalidateMode: AutovalidateMode.always,
                          key: _key,
                          child: Column(
                            children:
                              <Widget>[
                                 Flexible(flex: 1,
                                   child: TextFormField(
                                     validator: EmailValidator(errorText: 'Email invalid'),
                                    controller: emailcontrol,
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
                            ],
                          ),
                        ),
                      ),
                    ),

                    SizedBox(height: 30.0,),
                    Container(
                      margin: EdgeInsets.only(left: 20, right: 20),
                      height: 50,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(45),
                        color: Colors.purple[100],
                      ),
                      child: Center(
                        child: Form(
                          child: TextFormField(
                            controller: passwordcontrol,
                              decoration: InputDecoration(hintText: "Password",
                                  border: InputBorder.none,
                                  icon: Padding(
                                    padding: const EdgeInsets.only(left: 8),
                                    child: Icon(
                                      Icons.vpn_key,
                                    ),
                                  )
                              ),
                              obscureText: true,
                          ),
                        ),
                      ),
                    )

                  ]

              )
              ),
              SizedBox(height: 10,),
              FlutterPwValidator(width: 300,
                  height: 50,
                  minLength: 6,
                  onSuccess: (){
                  },
                  uppercaseCharCount: 1,
                  controller: passwordcontrol),
              SizedBox(height: 30,),

              Container(
                margin: EdgeInsets.only(left: 20,right: 20),
                height: 60,
                width: 250,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(45),
                  color: Colors.purple,
                ),
                child: MaterialButton(
                    child: Text("Sing Up",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                      ),
                    ),
                    onPressed: () async {
                      await authService.creatUserWithEmailandPassword(
                          emailcontrol.text,
                          passwordcontrol.text,
                           );
                      Navigator.pop(context);}

                ),
              ),
              SizedBox(height: 12,),

              Container(
                margin: EdgeInsets.only(top: 8),
                alignment: Alignment.bottomCenter,
                child: Text("Already have an accaunt?"),
              ),
              Container(
                margin: EdgeInsets.only(right: 10),
                alignment: Alignment.bottomRight,
                child: MaterialButton(
                  child: Text("Log in!",
                    style: TextStyle(
                        fontWeight: FontWeight.bold
                    ),),
                  onPressed: () {
                    Navigator.push(context, MaterialPageRoute(builder: (context) => SingIn()));
                  },
                ),
              )

            ],
          ),
        )
    );
  }
}

import 'package:app2/models/users.dart';
import 'package:firebase_auth/firebase_auth.dart' as auth;

class AuthService {

  final auth.FirebaseAuth _firebaseAuth = auth.FirebaseAuth.instance;

  User? _userFromFirebase(auth.User? user) {
    if (user == null) {
      return null;
    }
    return User(user.uid, user.email,);
  }

  Stream<User?>? get user {
    return _firebaseAuth.authStateChanges().map(_userFromFirebase);
  }

  Future<User?> singinWithEmailandPassword(String email,
      String password,) async {
    final credential = await _firebaseAuth.signInWithEmailAndPassword(
        email: email,
        password: password);

    return _userFromFirebase(credential.user);
  }


  Future<User?> creatUserWithEmailandPassword(String email,
      String password) async {
    final credential = await _firebaseAuth.createUserWithEmailAndPassword(
        email: email,
        password: password,);

    return _userFromFirebase(credential.user);
  }

  Future<void> singOut() async {
    return await _firebaseAuth.signOut();
  }


}



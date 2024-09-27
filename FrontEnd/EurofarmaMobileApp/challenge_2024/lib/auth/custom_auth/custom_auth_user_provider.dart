import 'package:rxdart/rxdart.dart';

import '/backend/schema/structs/index.dart';
import 'custom_auth_manager.dart';

class Challenge2024AuthUser {
  Challenge2024AuthUser({
    required this.loggedIn,
    this.uid,
    this.userData,
  });

  bool loggedIn;
  String? uid;
  UserStruct? userData;
}

/// Generates a stream of the authenticated user.
BehaviorSubject<Challenge2024AuthUser> challenge2024AuthUserSubject =
    BehaviorSubject.seeded(Challenge2024AuthUser(loggedIn: false));
Stream<Challenge2024AuthUser> challenge2024AuthUserStream() =>
    challenge2024AuthUserSubject
        .asBroadcastStream()
        .map((user) => currentUser = user);

// ignore_for_file: unnecessary_getters_setters

import '/backend/schema/util/schema_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class RefreshTokenStruct extends BaseStruct {
  RefreshTokenStruct({
    String? id,
    String? username,
    String? name,
    List<String>? roles,
    bool? authenticated,
    String? created,
    String? expiration,
    String? accessToken,
    String? refreshToken,
    String? instructorId,
  })  : _id = id,
        _username = username,
        _name = name,
        _roles = roles,
        _authenticated = authenticated,
        _created = created,
        _expiration = expiration,
        _accessToken = accessToken,
        _refreshToken = refreshToken,
        _instructorId = instructorId;

  // "id" field.
  String? _id;
  String get id => _id ?? '';
  set id(String? val) => _id = val;

  bool hasId() => _id != null;

  // "username" field.
  String? _username;
  String get username => _username ?? '';
  set username(String? val) => _username = val;

  bool hasUsername() => _username != null;

  // "name" field.
  String? _name;
  String get name => _name ?? '';
  set name(String? val) => _name = val;

  bool hasName() => _name != null;

  // "roles" field.
  List<String>? _roles;
  List<String> get roles => _roles ?? const [];
  set roles(List<String>? val) => _roles = val;

  void updateRoles(Function(List<String>) updateFn) {
    updateFn(_roles ??= []);
  }

  bool hasRoles() => _roles != null;

  // "authenticated" field.
  bool? _authenticated;
  bool get authenticated => _authenticated ?? false;
  set authenticated(bool? val) => _authenticated = val;

  bool hasAuthenticated() => _authenticated != null;

  // "created" field.
  String? _created;
  String get created => _created ?? '';
  set created(String? val) => _created = val;

  bool hasCreated() => _created != null;

  // "expiration" field.
  String? _expiration;
  String get expiration => _expiration ?? '';
  set expiration(String? val) => _expiration = val;

  bool hasExpiration() => _expiration != null;

  // "accessToken" field.
  String? _accessToken;
  String get accessToken => _accessToken ?? '';
  set accessToken(String? val) => _accessToken = val;

  bool hasAccessToken() => _accessToken != null;

  // "refreshToken" field.
  String? _refreshToken;
  String get refreshToken => _refreshToken ?? '';
  set refreshToken(String? val) => _refreshToken = val;

  bool hasRefreshToken() => _refreshToken != null;

  // "instructorId" field.
  String? _instructorId;
  String get instructorId => _instructorId ?? '';
  set instructorId(String? val) => _instructorId = val;

  bool hasInstructorId() => _instructorId != null;

  static RefreshTokenStruct fromMap(Map<String, dynamic> data) =>
      RefreshTokenStruct(
        id: data['id'] as String?,
        username: data['username'] as String?,
        name: data['name'] as String?,
        roles: getDataList(data['roles']),
        authenticated: data['authenticated'] as bool?,
        created: data['created'] as String?,
        expiration: data['expiration'] as String?,
        accessToken: data['accessToken'] as String?,
        refreshToken: data['refreshToken'] as String?,
        instructorId: data['instructorId'] as String?,
      );

  static RefreshTokenStruct? maybeFromMap(dynamic data) => data is Map
      ? RefreshTokenStruct.fromMap(data.cast<String, dynamic>())
      : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'username': _username,
        'name': _name,
        'roles': _roles,
        'authenticated': _authenticated,
        'created': _created,
        'expiration': _expiration,
        'accessToken': _accessToken,
        'refreshToken': _refreshToken,
        'instructorId': _instructorId,
      }.withoutNulls;

  @override
  Map<String, dynamic> toSerializableMap() => {
        'id': serializeParam(
          _id,
          ParamType.String,
        ),
        'username': serializeParam(
          _username,
          ParamType.String,
        ),
        'name': serializeParam(
          _name,
          ParamType.String,
        ),
        'roles': serializeParam(
          _roles,
          ParamType.String,
          isList: true,
        ),
        'authenticated': serializeParam(
          _authenticated,
          ParamType.bool,
        ),
        'created': serializeParam(
          _created,
          ParamType.String,
        ),
        'expiration': serializeParam(
          _expiration,
          ParamType.String,
        ),
        'accessToken': serializeParam(
          _accessToken,
          ParamType.String,
        ),
        'refreshToken': serializeParam(
          _refreshToken,
          ParamType.String,
        ),
        'instructorId': serializeParam(
          _instructorId,
          ParamType.String,
        ),
      }.withoutNulls;

  static RefreshTokenStruct fromSerializableMap(Map<String, dynamic> data) =>
      RefreshTokenStruct(
        id: deserializeParam(
          data['id'],
          ParamType.String,
          false,
        ),
        username: deserializeParam(
          data['username'],
          ParamType.String,
          false,
        ),
        name: deserializeParam(
          data['name'],
          ParamType.String,
          false,
        ),
        roles: deserializeParam<String>(
          data['roles'],
          ParamType.String,
          true,
        ),
        authenticated: deserializeParam(
          data['authenticated'],
          ParamType.bool,
          false,
        ),
        created: deserializeParam(
          data['created'],
          ParamType.String,
          false,
        ),
        expiration: deserializeParam(
          data['expiration'],
          ParamType.String,
          false,
        ),
        accessToken: deserializeParam(
          data['accessToken'],
          ParamType.String,
          false,
        ),
        refreshToken: deserializeParam(
          data['refreshToken'],
          ParamType.String,
          false,
        ),
        instructorId: deserializeParam(
          data['instructorId'],
          ParamType.String,
          false,
        ),
      );

  @override
  String toString() => 'RefreshTokenStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    const listEquality = ListEquality();
    return other is RefreshTokenStruct &&
        id == other.id &&
        username == other.username &&
        name == other.name &&
        listEquality.equals(roles, other.roles) &&
        authenticated == other.authenticated &&
        created == other.created &&
        expiration == other.expiration &&
        accessToken == other.accessToken &&
        refreshToken == other.refreshToken &&
        instructorId == other.instructorId;
  }

  @override
  int get hashCode => const ListEquality().hash([
        id,
        username,
        name,
        roles,
        authenticated,
        created,
        expiration,
        accessToken,
        refreshToken,
        instructorId
      ]);
}

RefreshTokenStruct createRefreshTokenStruct({
  String? id,
  String? username,
  String? name,
  bool? authenticated,
  String? created,
  String? expiration,
  String? accessToken,
  String? refreshToken,
  String? instructorId,
}) =>
    RefreshTokenStruct(
      id: id,
      username: username,
      name: name,
      authenticated: authenticated,
      created: created,
      expiration: expiration,
      accessToken: accessToken,
      refreshToken: refreshToken,
      instructorId: instructorId,
    );

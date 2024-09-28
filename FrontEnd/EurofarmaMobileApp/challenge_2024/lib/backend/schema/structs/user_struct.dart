// ignore_for_file: unnecessary_getters_setters

import '/backend/schema/util/schema_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class UserStruct extends BaseStruct {
  UserStruct({
    int? id,
    String? name,
    bool? authenticated,
    String? accessToken,
    String? refreshToken,
    List<String>? roles,
    int? instructorId,
    String? employeeRegistration,
    int? created,
    int? expiration,
  })  : _id = id,
        _name = name,
        _authenticated = authenticated,
        _accessToken = accessToken,
        _refreshToken = refreshToken,
        _roles = roles,
        _instructorId = instructorId,
        _employeeRegistration = employeeRegistration,
        _created = created,
        _expiration = expiration;

  // "id" field.
  int? _id;
  int get id => _id ?? 0;
  set id(int? val) => _id = val;

  void incrementId(int amount) => id = id + amount;

  bool hasId() => _id != null;

  // "name" field.
  String? _name;
  String get name => _name ?? '';
  set name(String? val) => _name = val;

  bool hasName() => _name != null;

  // "authenticated" field.
  bool? _authenticated;
  bool get authenticated => _authenticated ?? false;
  set authenticated(bool? val) => _authenticated = val;

  bool hasAuthenticated() => _authenticated != null;

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

  // "roles" field.
  List<String>? _roles;
  List<String> get roles => _roles ?? const [];
  set roles(List<String>? val) => _roles = val;

  void updateRoles(Function(List<String>) updateFn) {
    updateFn(_roles ??= []);
  }

  bool hasRoles() => _roles != null;

  // "instructorId" field.
  int? _instructorId;
  int get instructorId => _instructorId ?? 0;
  set instructorId(int? val) => _instructorId = val;

  void incrementInstructorId(int amount) =>
      instructorId = instructorId + amount;

  bool hasInstructorId() => _instructorId != null;

  // "employeeRegistration" field.
  String? _employeeRegistration;
  String get employeeRegistration => _employeeRegistration ?? '';
  set employeeRegistration(String? val) => _employeeRegistration = val;

  bool hasEmployeeRegistration() => _employeeRegistration != null;

  // "created" field.
  int? _created;
  int get created => _created ?? 0;
  set created(int? val) => _created = val;

  void incrementCreated(int amount) => created = created + amount;

  bool hasCreated() => _created != null;

  // "expiration" field.
  int? _expiration;
  int get expiration => _expiration ?? 0;
  set expiration(int? val) => _expiration = val;

  void incrementExpiration(int amount) => expiration = expiration + amount;

  bool hasExpiration() => _expiration != null;

  static UserStruct fromMap(Map<String, dynamic> data) => UserStruct(
        id: castToType<int>(data['id']),
        name: data['name'] as String?,
        authenticated: data['authenticated'] as bool?,
        accessToken: data['accessToken'] as String?,
        refreshToken: data['refreshToken'] as String?,
        roles: getDataList(data['roles']),
        instructorId: castToType<int>(data['instructorId']),
        employeeRegistration: data['employeeRegistration'] as String?,
        created: castToType<int>(data['created']),
        expiration: castToType<int>(data['expiration']),
      );

  static UserStruct? maybeFromMap(dynamic data) =>
      data is Map ? UserStruct.fromMap(data.cast<String, dynamic>()) : null;

  Map<String, dynamic> toMap() => {
        'id': _id,
        'name': _name,
        'authenticated': _authenticated,
        'accessToken': _accessToken,
        'refreshToken': _refreshToken,
        'roles': _roles,
        'instructorId': _instructorId,
        'employeeRegistration': _employeeRegistration,
        'created': _created,
        'expiration': _expiration,
      }.withoutNulls;

  @override
  Map<String, dynamic> toSerializableMap() => {
        'id': serializeParam(
          _id,
          ParamType.int,
        ),
        'name': serializeParam(
          _name,
          ParamType.String,
        ),
        'authenticated': serializeParam(
          _authenticated,
          ParamType.bool,
        ),
        'accessToken': serializeParam(
          _accessToken,
          ParamType.String,
        ),
        'refreshToken': serializeParam(
          _refreshToken,
          ParamType.String,
        ),
        'roles': serializeParam(
          _roles,
          ParamType.String,
          isList: true,
        ),
        'instructorId': serializeParam(
          _instructorId,
          ParamType.int,
        ),
        'employeeRegistration': serializeParam(
          _employeeRegistration,
          ParamType.String,
        ),
        'created': serializeParam(
          _created,
          ParamType.int,
        ),
        'expiration': serializeParam(
          _expiration,
          ParamType.int,
        ),
      }.withoutNulls;

  static UserStruct fromSerializableMap(Map<String, dynamic> data) =>
      UserStruct(
        id: deserializeParam(
          data['id'],
          ParamType.int,
          false,
        ),
        name: deserializeParam(
          data['name'],
          ParamType.String,
          false,
        ),
        authenticated: deserializeParam(
          data['authenticated'],
          ParamType.bool,
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
        roles: deserializeParam<String>(
          data['roles'],
          ParamType.String,
          true,
        ),
        instructorId: deserializeParam(
          data['instructorId'],
          ParamType.int,
          false,
        ),
        employeeRegistration: deserializeParam(
          data['employeeRegistration'],
          ParamType.String,
          false,
        ),
        created: deserializeParam(
          data['created'],
          ParamType.int,
          false,
        ),
        expiration: deserializeParam(
          data['expiration'],
          ParamType.int,
          false,
        ),
      );

  @override
  String toString() => 'UserStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    const listEquality = ListEquality();
    return other is UserStruct &&
        id == other.id &&
        name == other.name &&
        authenticated == other.authenticated &&
        accessToken == other.accessToken &&
        refreshToken == other.refreshToken &&
        listEquality.equals(roles, other.roles) &&
        instructorId == other.instructorId &&
        employeeRegistration == other.employeeRegistration &&
        created == other.created &&
        expiration == other.expiration;
  }

  @override
  int get hashCode => const ListEquality().hash([
        id,
        name,
        authenticated,
        accessToken,
        refreshToken,
        roles,
        instructorId,
        employeeRegistration,
        created,
        expiration
      ]);
}

UserStruct createUserStruct({
  int? id,
  String? name,
  bool? authenticated,
  String? accessToken,
  String? refreshToken,
  int? instructorId,
  String? employeeRegistration,
  int? created,
  int? expiration,
}) =>
    UserStruct(
      id: id,
      name: name,
      authenticated: authenticated,
      accessToken: accessToken,
      refreshToken: refreshToken,
      instructorId: instructorId,
      employeeRegistration: employeeRegistration,
      created: created,
      expiration: expiration,
    );

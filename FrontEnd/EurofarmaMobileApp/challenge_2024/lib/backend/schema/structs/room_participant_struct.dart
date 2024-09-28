// ignore_for_file: unnecessary_getters_setters

import '/backend/schema/util/schema_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class RoomParticipantStruct extends BaseStruct {
  RoomParticipantStruct({
    String? participantFullName,
    int? employeeRegistration,
    String? registrationDate,
  })  : _participantFullName = participantFullName,
        _employeeRegistration = employeeRegistration,
        _registrationDate = registrationDate;

  // "participantFullName" field.
  String? _participantFullName;
  String get participantFullName => _participantFullName ?? '';
  set participantFullName(String? val) => _participantFullName = val;

  bool hasParticipantFullName() => _participantFullName != null;

  // "employeeRegistration" field.
  int? _employeeRegistration;
  int get employeeRegistration => _employeeRegistration ?? 0;
  set employeeRegistration(int? val) => _employeeRegistration = val;

  void incrementEmployeeRegistration(int amount) =>
      employeeRegistration = employeeRegistration + amount;

  bool hasEmployeeRegistration() => _employeeRegistration != null;

  // "registrationDate" field.
  String? _registrationDate;
  String get registrationDate => _registrationDate ?? '';
  set registrationDate(String? val) => _registrationDate = val;

  bool hasRegistrationDate() => _registrationDate != null;

  static RoomParticipantStruct fromMap(Map<String, dynamic> data) =>
      RoomParticipantStruct(
        participantFullName: data['participantFullName'] as String?,
        employeeRegistration: castToType<int>(data['employeeRegistration']),
        registrationDate: data['registrationDate'] as String?,
      );

  static RoomParticipantStruct? maybeFromMap(dynamic data) => data is Map
      ? RoomParticipantStruct.fromMap(data.cast<String, dynamic>())
      : null;

  Map<String, dynamic> toMap() => {
        'participantFullName': _participantFullName,
        'employeeRegistration': _employeeRegistration,
        'registrationDate': _registrationDate,
      }.withoutNulls;

  @override
  Map<String, dynamic> toSerializableMap() => {
        'participantFullName': serializeParam(
          _participantFullName,
          ParamType.String,
        ),
        'employeeRegistration': serializeParam(
          _employeeRegistration,
          ParamType.int,
        ),
        'registrationDate': serializeParam(
          _registrationDate,
          ParamType.String,
        ),
      }.withoutNulls;

  static RoomParticipantStruct fromSerializableMap(Map<String, dynamic> data) =>
      RoomParticipantStruct(
        participantFullName: deserializeParam(
          data['participantFullName'],
          ParamType.String,
          false,
        ),
        employeeRegistration: deserializeParam(
          data['employeeRegistration'],
          ParamType.int,
          false,
        ),
        registrationDate: deserializeParam(
          data['registrationDate'],
          ParamType.String,
          false,
        ),
      );

  @override
  String toString() => 'RoomParticipantStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    return other is RoomParticipantStruct &&
        participantFullName == other.participantFullName &&
        employeeRegistration == other.employeeRegistration &&
        registrationDate == other.registrationDate;
  }

  @override
  int get hashCode => const ListEquality()
      .hash([participantFullName, employeeRegistration, registrationDate]);
}

RoomParticipantStruct createRoomParticipantStruct({
  String? participantFullName,
  int? employeeRegistration,
  String? registrationDate,
}) =>
    RoomParticipantStruct(
      participantFullName: participantFullName,
      employeeRegistration: employeeRegistration,
      registrationDate: registrationDate,
    );

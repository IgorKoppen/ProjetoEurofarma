// ignore_for_file: unnecessary_getters_setters

import '/backend/schema/util/schema_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class HistoricoConversaStruct extends BaseStruct {
  HistoricoConversaStruct({
    String? pergunta,
    String? user,
    List<String>? context,
  })  : _pergunta = pergunta,
        _user = user,
        _context = context;

  // "pergunta" field.
  String? _pergunta;
  String get pergunta => _pergunta ?? '';
  set pergunta(String? val) => _pergunta = val;

  bool hasPergunta() => _pergunta != null;

  // "user" field.
  String? _user;
  String get user => _user ?? '';
  set user(String? val) => _user = val;

  bool hasUser() => _user != null;

  // "context" field.
  List<String>? _context;
  List<String> get context => _context ?? const [];
  set context(List<String>? val) => _context = val;

  void updateContext(Function(List<String>) updateFn) {
    updateFn(_context ??= []);
  }

  bool hasContext() => _context != null;

  static HistoricoConversaStruct fromMap(Map<String, dynamic> data) =>
      HistoricoConversaStruct(
        pergunta: data['pergunta'] as String?,
        user: data['user'] as String?,
        context: getDataList(data['context']),
      );

  static HistoricoConversaStruct? maybeFromMap(dynamic data) => data is Map
      ? HistoricoConversaStruct.fromMap(data.cast<String, dynamic>())
      : null;

  Map<String, dynamic> toMap() => {
        'pergunta': _pergunta,
        'user': _user,
        'context': _context,
      }.withoutNulls;

  @override
  Map<String, dynamic> toSerializableMap() => {
        'pergunta': serializeParam(
          _pergunta,
          ParamType.String,
        ),
        'user': serializeParam(
          _user,
          ParamType.String,
        ),
        'context': serializeParam(
          _context,
          ParamType.String,
          isList: true,
        ),
      }.withoutNulls;

  static HistoricoConversaStruct fromSerializableMap(
          Map<String, dynamic> data) =>
      HistoricoConversaStruct(
        pergunta: deserializeParam(
          data['pergunta'],
          ParamType.String,
          false,
        ),
        user: deserializeParam(
          data['user'],
          ParamType.String,
          false,
        ),
        context: deserializeParam<String>(
          data['context'],
          ParamType.String,
          true,
        ),
      );

  @override
  String toString() => 'HistoricoConversaStruct(${toMap()})';

  @override
  bool operator ==(Object other) {
    const listEquality = ListEquality();
    return other is HistoricoConversaStruct &&
        pergunta == other.pergunta &&
        user == other.user &&
        listEquality.equals(context, other.context);
  }

  @override
  int get hashCode => const ListEquality().hash([pergunta, user, context]);
}

HistoricoConversaStruct createHistoricoConversaStruct({
  String? pergunta,
  String? user,
}) =>
    HistoricoConversaStruct(
      pergunta: pergunta,
      user: user,
    );

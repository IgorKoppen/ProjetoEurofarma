import 'dart:convert';

import 'package:flutter/foundation.dart';

import '/flutter_flow/flutter_flow_util.dart';
import 'api_manager.dart';

export 'api_manager.dart' show ApiCallResponse;

const _kPrivateApiFunctionName = 'ffPrivateApiCall';

/// Start Employee Group Code

class EmployeeGroup {
  static String getBaseUrl({
    String? token = '',
  }) =>
      'https://closing-lively-woodcock.ngrok-free.app/eurofarma/employee';
  static Map<String, String> headers = {
    'Authorization': 'Bearer [Token]',
  };
  static UpdatePasswordeCall updatePasswordeCall = UpdatePasswordeCall();
  static UpdateCellPhoneCall updateCellPhoneCall = UpdateCellPhoneCall();
  static FindEmployeeUserProfileInfoCall findEmployeeUserProfileInfoCall =
      FindEmployeeUserProfileInfoCall();
}

class UpdatePasswordeCall {
  Future<ApiCallResponse> call({
    String? newPassword = '',
    int? id,
    String? oldPassword = '',
    String? token = '',
  }) async {
    final baseUrl = EmployeeGroup.getBaseUrl(
      token: token,
    );

    final ffApiRequestBody = '''
{
  "oldPassword": "$oldPassword",
  "newPassword": "$newPassword"
}''';
    return ApiManager.instance.makeApiCall(
      callName: 'UpdatePassworde',
      apiUrl: '$baseUrl/updatePassword/$id',
      callType: ApiCallType.PATCH,
      headers: {
        'Authorization': 'Bearer $token',
        'Content-Type': 'application/json',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: true,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class UpdateCellPhoneCall {
  Future<ApiCallResponse> call({
    int? userId,
    String? password = '',
    String? phone = '',
    String? token = '',
  }) async {
    final baseUrl = EmployeeGroup.getBaseUrl(
      token: token,
    );

    final ffApiRequestBody = '''
{
  "phoneNumber": "$phone",
  "password": "$password"
}''';
    return ApiManager.instance.makeApiCall(
      callName: 'UpdateCellPhone',
      apiUrl: '$baseUrl/changePhone/$userId',
      callType: ApiCallType.PATCH,
      headers: {
        'Authorization': 'Bearer $token',
        'Content-Type': 'application/json',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: true,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class FindEmployeeUserProfileInfoCall {
  Future<ApiCallResponse> call({
    int? id,
    String? token = '',
  }) async {
    final baseUrl = EmployeeGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'FindEmployeeUserProfileInfo',
      apiUrl: '$baseUrl/profile/$id',
      callType: ApiCallType.GET,
      headers: {
        'Authorization': 'Bearer $token',
        'ngrok-skip-browser-warning': 'banana',
        'Access-Control-Allow-Origin': '*',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

/// End Employee Group Code

/// Start Instructor Group Code

class InstructorGroup {
  static String getBaseUrl({
    String? token = '',
  }) =>
      'https://closing-lively-woodcock.ngrok-free.app/eurofarma';
  static Map<String, String> headers = {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer [Token]',
    'Access-Control-Allow-Origin': '*',
  };
  static FindAllInstructorsCall findAllInstructorsCall =
      FindAllInstructorsCall();
}

class FindAllInstructorsCall {
  Future<ApiCallResponse> call({
    String? token = '',
  }) async {
    final baseUrl = InstructorGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'FindAllInstructors',
      apiUrl: '$baseUrl/instructor/findAllFullNameEmployeeAndRegistration',
      callType: ApiCallType.GET,
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  List<int>? id(dynamic response) => (getJsonField(
        response,
        r'''$[:].id''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<int>(x))
          .withoutNulls
          .toList();
  List<String>? fullName(dynamic response) => (getJsonField(
        response,
        r'''$[:].fullName''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
  List<String>? name(dynamic response) => (getJsonField(
        response,
        r'''$[:].name''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
  List<String>? surname(dynamic response) => (getJsonField(
        response,
        r'''$[:].surname''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
  List<int>? employeeRegistrarion(dynamic response) => (getJsonField(
        response,
        r'''$[:].employeeRegistrarion''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<int>(x))
          .withoutNulls
          .toList();
}

/// End Instructor Group Code

/// Start Department Group Code

class DepartmentGroup {
  static String getBaseUrl({
    String? token = '',
  }) =>
      'https://closing-lively-woodcock.ngrok-free.app/eurofarma';
  static Map<String, String> headers = {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer [Token]',
    'Access-Control-Allow-Origin': '*',
  };
  static FindAllDepartmentCall findAllDepartmentCall = FindAllDepartmentCall();
}

class FindAllDepartmentCall {
  Future<ApiCallResponse> call({
    String? token = '',
  }) async {
    final baseUrl = DepartmentGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'FindAllDepartment',
      apiUrl: '$baseUrl/department',
      callType: ApiCallType.GET,
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  List? departments(dynamic response) => getJsonField(
        response,
        r'''$''',
        true,
      ) as List?;
  List<String>? departName(dynamic response) => (getJsonField(
        response,
        r'''$[:].departName''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
  int? departId(dynamic response) => castToType<int>(getJsonField(
        response,
        r'''$[:].id''',
      ));
}

/// End Department Group Code

/// Start Tags Group Code

class TagsGroup {
  static String getBaseUrl({
    String? token = '',
  }) =>
      'https://closing-lively-woodcock.ngrok-free.app/eurofarma';
  static Map<String, String> headers = {
    'Authorization': 'Bearer [Token]',
    'Access-Control-Allow-Origin': '*',
  };
  static InsertTagCall insertTagCall = InsertTagCall();
  static FindTagCall findTagCall = FindTagCall();
  static FindAllCall findAllCall = FindAllCall();
}

class InsertTagCall {
  Future<ApiCallResponse> call({
    String? name = '',
    String? color = '',
    String? token = '',
  }) async {
    final baseUrl = TagsGroup.getBaseUrl(
      token: token,
    );

    final ffApiRequestBody = '''
{
  "name": "$name",
  "color": "$color"
}''';
    return ApiManager.instance.makeApiCall(
      callName: 'InsertTag',
      apiUrl: '$baseUrl/tag',
      callType: ApiCallType.POST,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: false,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class FindTagCall {
  Future<ApiCallResponse> call({
    int? id,
    String? token = '',
  }) async {
    final baseUrl = TagsGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'FindTag',
      apiUrl: '$baseUrl/tag/$id',
      callType: ApiCallType.GET,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: false,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  String? name(dynamic response) => castToType<String>(getJsonField(
        response,
        r'''$.name''',
      ));
  String? color(dynamic response) => castToType<String>(getJsonField(
        response,
        r'''$.color''',
      ));
}

class FindAllCall {
  Future<ApiCallResponse> call({
    String? token = '',
  }) async {
    final baseUrl = TagsGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'FindAll',
      apiUrl: '$baseUrl/tag',
      callType: ApiCallType.GET,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  List? tags(dynamic response) => getJsonField(
        response,
        r'''$''',
        true,
      ) as List?;
  List<int>? tagID(dynamic response) => (getJsonField(
        response,
        r'''$[:].id''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<int>(x))
          .withoutNulls
          .toList();
  List<String>? tagName(dynamic response) => (getJsonField(
        response,
        r'''$[:].name''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
  List<String>? tagColor(dynamic response) => (getJsonField(
        response,
        r'''$[:].color''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
}

/// End Tags Group Code

/// Start Training Group Code

class TrainingGroup {
  static String getBaseUrl({
    String? token = '',
  }) =>
      'https://closing-lively-woodcock.ngrok-free.app/eurofarma';
  static Map<String, String> headers = {
    'Authorization': 'Bearer [Token]',
    'Access-Control-Allow-Origin': '*',
  };
  static CreateTrainningCall createTrainningCall = CreateTrainningCall();
  static AddEmployeeCall addEmployeeCall = AddEmployeeCall();
  static EditTrainingCall editTrainingCall = EditTrainingCall();
  static GetRoomByCodeCall getRoomByCodeCall = GetRoomByCodeCall();
  static ConfirmRoomPasswordCall confirmRoomPasswordCall =
      ConfirmRoomPasswordCall();
  static GetAllRoomParticipantsCall getAllRoomParticipantsCall =
      GetAllRoomParticipantsCall();
  static FindInstructorTrainingsByInstructorIdCall
      findInstructorTrainingsByInstructorIdCall =
      FindInstructorTrainingsByInstructorIdCall();
  static FindEmployeeTrainingsByEmployeeIdCall
      findEmployeeTrainingsByEmployeeIdCall =
      FindEmployeeTrainingsByEmployeeIdCall();
  static FindInstructorTrainingsCall findInstructorTrainingsCall =
      FindInstructorTrainingsCall();
  static DeleteTrainingCall deleteTrainingCall = DeleteTrainingCall();
}

class CreateTrainningCall {
  Future<ApiCallResponse> call({
    String? name = '',
    String? closingDate = '',
    String? description = '',
    List<int>? instructorList,
    List<int>? tagsList,
    List<int>? departmentList,
    bool? isToSendMessage,
    bool? hasQuiz,
    int? quiz,
    String? token = '',
  }) async {
    final baseUrl = TrainingGroup.getBaseUrl(
      token: token,
    );
    final instructor = _serializeList(instructorList);
    final tags = _serializeList(tagsList);
    final department = _serializeList(departmentList);

    final ffApiRequestBody = '''
{
  "name": "$name",
  "closing_date": "$closingDate",
  "description": "$description",
  "instructor": $instructor,
  "tags": $tags,
  "department": $department,
  "isToSendMessage": $isToSendMessage,
  "hasQuiz": $hasQuiz,
  "quiz": $quiz
}''';
    return ApiManager.instance.makeApiCall(
      callName: 'CreateTrainning',
      apiUrl: '$baseUrl/training',
      callType: ApiCallType.POST,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: true,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class AddEmployeeCall {
  Future<ApiCallResponse> call({
    String? code = '',
    String? password = '',
    int? id,
    String? assinatura = '',
    int? quizTries,
    double? nota,
    String? token = '',
  }) async {
    final baseUrl = TrainingGroup.getBaseUrl(
      token: token,
    );

    final ffApiRequestBody = '''
{
  "userId": $id,
  "code": "$code",
  "password": "$password",
  "signature": "$assinatura",
  "quizTries": $quizTries,
  "nota": $nota
}''';
    return ApiManager.instance.makeApiCall(
      callName: 'AddEmployee',
      apiUrl: '$baseUrl/training/addEmployee',
      callType: ApiCallType.PUT,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class EditTrainingCall {
  Future<ApiCallResponse> call({
    int? id,
    String? name = '',
    String? closingDate = '',
    String? description = '',
    List<int>? instructorList,
    List<int>? tagsList,
    bool? hasQuiz,
    int? quiz,
    bool? isToSendMessage,
    List<int>? departmentList,
    String? token = '',
  }) async {
    final baseUrl = TrainingGroup.getBaseUrl(
      token: token,
    );
    final instructor = _serializeList(instructorList);
    final tags = _serializeList(tagsList);
    final department = _serializeList(departmentList);

    final ffApiRequestBody = '''
{
  "name": "$name",
  "closing_date": "$closingDate",
  "description": "$description",
  "instructor": $instructor,
  "tags": $tags,
  "department": $department,
  "isToSendMessage": $isToSendMessage,
  "hasQuiz": $hasQuiz,
  "quiz": $quiz
}''';
    return ApiManager.instance.makeApiCall(
      callName: 'EditTraining',
      apiUrl: '$baseUrl/training/$id',
      callType: ApiCallType.PUT,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: true,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class GetRoomByCodeCall {
  Future<ApiCallResponse> call({
    String? code = '',
    int? id,
    String? token = '',
  }) async {
    final baseUrl = TrainingGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'getRoomByCode',
      apiUrl: '$baseUrl/training/getRoomByCode/$id/$code',
      callType: ApiCallType.GET,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  String? title(dynamic response) => castToType<String>(getJsonField(
        response,
        r'''$.name''',
      ));
  String? description(dynamic response) => castToType<String>(getJsonField(
        response,
        r'''$.description''',
      ));
  bool? hazQuiz(dynamic response) => castToType<bool>(getJsonField(
        response,
        r'''$.hasQuiz''',
      ));
  int? quizId(dynamic response) => castToType<int>(getJsonField(
        response,
        r'''$.quiz.id''',
      ));
}

class ConfirmRoomPasswordCall {
  Future<ApiCallResponse> call({
    String? code = '',
    String? password = '',
    int? userId,
    String? token = '',
  }) async {
    final baseUrl = TrainingGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'confirmRoomPassword',
      apiUrl: '$baseUrl/training/confirmPassword',
      callType: ApiCallType.GET,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {
        'code': code,
        'password': password,
        'userId': userId,
      },
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: true,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class GetAllRoomParticipantsCall {
  Future<ApiCallResponse> call({
    int? roomId,
    String? token = '',
  }) async {
    final baseUrl = TrainingGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'getAllRoomParticipants',
      apiUrl: '$baseUrl/training/getAllRoomParticipants/$roomId',
      callType: ApiCallType.GET,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class FindInstructorTrainingsByInstructorIdCall {
  Future<ApiCallResponse> call({
    int? id,
    String? token = '',
  }) async {
    final baseUrl = TrainingGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'FindInstructorTrainingsByInstructorId',
      apiUrl: '$baseUrl/training/InstructorTrainings/$id',
      callType: ApiCallType.GET,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  List<String>? tagsName(dynamic response) => (getJsonField(
        response,
        r'''$[:].tags[:].name''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
}

class FindEmployeeTrainingsByEmployeeIdCall {
  Future<ApiCallResponse> call({
    int? id,
    String? token = '',
  }) async {
    final baseUrl = TrainingGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'FindEmployeeTrainingsByEmployeeId',
      apiUrl: '$baseUrl/training/EmployeeTrainings/$id',
      callType: ApiCallType.GET,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class FindInstructorTrainingsCall {
  Future<ApiCallResponse> call({
    int? id,
    String? tag = '',
    String? token = '',
  }) async {
    final baseUrl = TrainingGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'FindInstructorTrainings',
      apiUrl: '$baseUrl/training/findTrainingByInstructorAndTag/$id',
      callType: ApiCallType.GET,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {
        'tag': tag,
      },
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class DeleteTrainingCall {
  Future<ApiCallResponse> call({
    int? id,
    String? token = '',
  }) async {
    final baseUrl = TrainingGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'DeleteTraining',
      apiUrl: '$baseUrl/training/$id',
      callType: ApiCallType.DELETE,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: false,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

/// End Training Group Code

/// Start Quiz Group Code

class QuizGroup {
  static String getBaseUrl({
    String? token = '',
  }) =>
      'https://closing-lively-woodcock.ngrok-free.app/eurofarma';
  static Map<String, String> headers = {
    'Authorization': 'Bearer [Token]',
    'Access-Control-Allow-Origin': '*',
  };
  static GetAllQuizCall getAllQuizCall = GetAllQuizCall();
  static FindByIdCall findByIdCall = FindByIdCall();
  static InsertQuizCall insertQuizCall = InsertQuizCall();
  static UpdateQuizCall updateQuizCall = UpdateQuizCall();
  static DeleteQuizCall deleteQuizCall = DeleteQuizCall();
  static ValidateQuizCall validateQuizCall = ValidateQuizCall();
}

class GetAllQuizCall {
  Future<ApiCallResponse> call({
    String? token = '',
  }) async {
    final baseUrl = QuizGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'getAllQuiz',
      apiUrl: '$baseUrl/quiz',
      callType: ApiCallType.GET,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  List<int>? quizId(dynamic response) => (getJsonField(
        response,
        r'''$[:].id''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<int>(x))
          .withoutNulls
          .toList();
  List<String>? quizName(dynamic response) => (getJsonField(
        response,
        r'''$[:].nome''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
  List<String>? quizDescription(dynamic response) => (getJsonField(
        response,
        r'''$[:].description''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
  List<int>? quizNotaMinima(dynamic response) => (getJsonField(
        response,
        r'''$[:].notaMinima''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<int>(x))
          .withoutNulls
          .toList();
  List<int>? quizNumeroDeQuestoes(dynamic response) => (getJsonField(
        response,
        r'''$[:].questionsNumber''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<int>(x))
          .withoutNulls
          .toList();
}

class FindByIdCall {
  Future<ApiCallResponse> call({
    int? id,
    String? token = '',
  }) async {
    final baseUrl = QuizGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'findById',
      apiUrl: '$baseUrl/quiz/$id',
      callType: ApiCallType.GET,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  int? quizId(dynamic response) => castToType<int>(getJsonField(
        response,
        r'''$.id''',
      ));
  String? quizName(dynamic response) => castToType<String>(getJsonField(
        response,
        r'''$.nome''',
      ));
  String? quizDescription(dynamic response) => castToType<String>(getJsonField(
        response,
        r'''$.description''',
      ));
  int? notaMinima(dynamic response) => castToType<int>(getJsonField(
        response,
        r'''$.notaMinima''',
      ));
  int? questionsNumber(dynamic response) => castToType<int>(getJsonField(
        response,
        r'''$.questionsNumber''',
      ));
  List<int>? questionsIds(dynamic response) => (getJsonField(
        response,
        r'''$.questions[:].id''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<int>(x))
          .withoutNulls
          .toList();
  List<String>? questionQuestion(dynamic response) => (getJsonField(
        response,
        r'''$.questions[:].question''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
  List<int>? answersIds(dynamic response) => (getJsonField(
        response,
        r'''$.questions[:].answers[:].id''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<int>(x))
          .withoutNulls
          .toList();
  List<String>? answersAnswers(dynamic response) => (getJsonField(
        response,
        r'''$.questions[:].answers[:].answer''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
  List<bool>? answersCorrect(dynamic response) => (getJsonField(
        response,
        r'''$.questions[:].answers[:].correct''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<bool>(x))
          .withoutNulls
          .toList();
}

class InsertQuizCall {
  Future<ApiCallResponse> call({
    String? nome = '',
    String? description = '',
    int? notaMinima,
    int? questionsNumber,
    String? token = '',
  }) async {
    final baseUrl = QuizGroup.getBaseUrl(
      token: token,
    );

    final ffApiRequestBody = '''
{
  "nome": "$nome",
  "description": "$description",
  "notaMinima": $notaMinima,
  "questionsNumber": $questionsNumber
}''';
    return ApiManager.instance.makeApiCall(
      callName: 'insertQuiz',
      apiUrl: '$baseUrl/quiz',
      callType: ApiCallType.POST,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: true,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  int? quizId(dynamic response) => castToType<int>(getJsonField(
        response,
        r'''$.id''',
      ));
  String? quizNome(dynamic response) => castToType<String>(getJsonField(
        response,
        r'''$.nome''',
      ));
  int? questionNumber(dynamic response) => castToType<int>(getJsonField(
        response,
        r'''$.questionsNumber''',
      ));
}

class UpdateQuizCall {
  Future<ApiCallResponse> call({
    int? id,
    String? nome = '',
    String? description = '',
    int? notaMinima,
    int? questionsNumber,
    List<int>? questionsIdList,
    String? token = '',
  }) async {
    final baseUrl = QuizGroup.getBaseUrl(
      token: token,
    );
    final questionsId = _serializeList(questionsIdList);

    final ffApiRequestBody = '''
{
  "nome": "$nome",
  "description": "$description",
  "notaMinima": $notaMinima,
  "questionsNumber": $questionsNumber,
  "questions": {
    "id": $questionsId
  }
}''';
    return ApiManager.instance.makeApiCall(
      callName: 'updateQuiz',
      apiUrl: '$baseUrl/quiz/$id',
      callType: ApiCallType.PUT,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: true,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class DeleteQuizCall {
  Future<ApiCallResponse> call({
    int? id,
    String? token = '',
  }) async {
    final baseUrl = QuizGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'deleteQuiz',
      apiUrl: '$baseUrl/quiz/$id',
      callType: ApiCallType.DELETE,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: false,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class ValidateQuizCall {
  Future<ApiCallResponse> call({
    int? id,
    List<int>? questionIdsList,
    List<String>? userAnswersList,
    String? token = '',
  }) async {
    final baseUrl = QuizGroup.getBaseUrl(
      token: token,
    );
    final questionIds = _serializeList(questionIdsList);
    final userAnswers = _serializeList(userAnswersList);

    final ffApiRequestBody = '''
{
  "questionIds": $questionIds,
  "userAnswers": $userAnswers
}''';
    return ApiManager.instance.makeApiCall(
      callName: 'validateQuiz',
      apiUrl: '$baseUrl/quiz/validate/$id',
      callType: ApiCallType.POST,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: true,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  bool? passed(dynamic response) => castToType<bool>(getJsonField(
        response,
        r'''$.passed''',
      ));
  double? score(dynamic response) => castToType<double>(getJsonField(
        response,
        r'''$.score''',
      ));
}

/// End Quiz Group Code

/// Start Question Group Code

class QuestionGroup {
  static String getBaseUrl({
    String? token = '',
  }) =>
      'https://closing-lively-woodcock.ngrok-free.app/eurofarma';
  static Map<String, String> headers = {
    'Authorization': 'Bearer [Token]',
    'Access-Control-Allow-Origin': '*',
  };
  static InsertQuestionCall insertQuestionCall = InsertQuestionCall();
  static FindByIdQuestionCall findByIdQuestionCall = FindByIdQuestionCall();
  static DeleteQuestionCall deleteQuestionCall = DeleteQuestionCall();
  static UpdateQuestionCall updateQuestionCall = UpdateQuestionCall();
}

class InsertQuestionCall {
  Future<ApiCallResponse> call({
    String? question = '',
    int? quizId,
    List<int>? answerIdsList,
    String? token = '',
  }) async {
    final baseUrl = QuestionGroup.getBaseUrl(
      token: token,
    );
    final answerIds = _serializeList(answerIdsList);

    final ffApiRequestBody = '''
{
  "question": "$question",
  "quizId": $quizId,
  "answerIds": $answerIds
}''';
    return ApiManager.instance.makeApiCall(
      callName: 'insertQuestion',
      apiUrl: '$baseUrl/question',
      callType: ApiCallType.POST,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: true,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class FindByIdQuestionCall {
  Future<ApiCallResponse> call({
    int? id,
    String? token = '',
  }) async {
    final baseUrl = QuestionGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'findByIdQuestion',
      apiUrl: '$baseUrl/question/$id',
      callType: ApiCallType.GET,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
        'ngrok-skip-browser-warning': 'banana',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  List<int>? answersId(dynamic response) => (getJsonField(
        response,
        r'''$.answers[:].id''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<int>(x))
          .withoutNulls
          .toList();
  String? question(dynamic response) => castToType<String>(getJsonField(
        response,
        r'''$.question''',
      ));
  int? questionId(dynamic response) => castToType<int>(getJsonField(
        response,
        r'''$.id''',
      ));
  List<String>? answersNames(dynamic response) => (getJsonField(
        response,
        r'''$.answers[:].answer''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
  List<bool>? answersIsCorrect(dynamic response) => (getJsonField(
        response,
        r'''$.answers[:].correct''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<bool>(x))
          .withoutNulls
          .toList();
}

class DeleteQuestionCall {
  Future<ApiCallResponse> call({
    int? id,
    String? token = '',
  }) async {
    final baseUrl = QuestionGroup.getBaseUrl(
      token: token,
    );

    return ApiManager.instance.makeApiCall(
      callName: 'deleteQuestion',
      apiUrl: '$baseUrl/question/$id',
      callType: ApiCallType.DELETE,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
      },
      params: {},
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: false,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

class UpdateQuestionCall {
  Future<ApiCallResponse> call({
    int? id,
    String? question = '',
    int? quizId,
    List<int>? answerIdsList,
    String? token = '',
  }) async {
    final baseUrl = QuestionGroup.getBaseUrl(
      token: token,
    );
    final answerIds = _serializeList(answerIdsList);

    final ffApiRequestBody = '''
{
  "question": "$question",
  "quizId": $quizId,
  "answerIds": $answerIds
}''';
    return ApiManager.instance.makeApiCall(
      callName: 'updateQuestion',
      apiUrl: '$baseUrl/question/$id',
      callType: ApiCallType.PUT,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: true,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }
}

/// End Question Group Code

/// Start Answer Group Code

class AnswerGroup {
  static String getBaseUrl({
    String? token = '',
  }) =>
      'https://closing-lively-woodcock.ngrok-free.app/eurofarma';
  static Map<String, String> headers = {
    'Authorization': 'Bearer [Token]',
    'Access-Control-Allow-Origin': '*',
  };
  static InsertAllCall insertAllCall = InsertAllCall();
  static UpdateAllCall updateAllCall = UpdateAllCall();
}

class InsertAllCall {
  Future<ApiCallResponse> call({
    dynamic answerInsertDTOsJson,
    String? token = '',
  }) async {
    final baseUrl = AnswerGroup.getBaseUrl(
      token: token,
    );

    final answerInsertDTOs = _serializeJson(answerInsertDTOsJson);
    final ffApiRequestBody = answerInsertDTOs;
    return ApiManager.instance.makeApiCall(
      callName: 'insertAll',
      apiUrl: '$baseUrl/answer',
      callType: ApiCallType.POST,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: true,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  List<int>? answersIds(dynamic response) => (getJsonField(
        response,
        r'''$[:].id''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<int>(x))
          .withoutNulls
          .toList();
}

class UpdateAllCall {
  Future<ApiCallResponse> call({
    dynamic answerUpdateDTOsJson,
    List<int>? answerIdsList,
    String? token = '',
  }) async {
    final baseUrl = AnswerGroup.getBaseUrl(
      token: token,
    );
    final answerIds = _serializeList(answerIdsList);
    final answerUpdateDTOs = _serializeJson(answerUpdateDTOsJson);
    final ffApiRequestBody = answerUpdateDTOs;
    return ApiManager.instance.makeApiCall(
      callName: 'updateAll',
      apiUrl: '$baseUrl/answer?existingIds=$answerIds',
      callType: ApiCallType.PUT,
      headers: {
        'Authorization': 'Bearer $token',
        'Access-Control-Allow-Origin': '*',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: true,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  List<int>? answersIds(dynamic response) => (getJsonField(
        response,
        r'''$[:].id''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<int>(x))
          .withoutNulls
          .toList();
}

/// End Answer Group Code

class AuthCall {
  static Future<ApiCallResponse> call({
    int? userName,
    String? password = '',
  }) async {
    final ffApiRequestBody = '''
{
  "userName": $userName,
  "password": "$password"
}''';
    return ApiManager.instance.makeApiCall(
      callName: 'Auth',
      apiUrl: 'https://closing-lively-woodcock.ngrok-free.app/auth/signin',
      callType: ApiCallType.POST,
      headers: {
        'Content-Type': 'application/json',
        'Accept': '*/*',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: true,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  static double? created(dynamic response) => castToType<double>(getJsonField(
        response,
        r'''$.created''',
      ));
  static double? expiration(dynamic response) =>
      castToType<double>(getJsonField(
        response,
        r'''$.expiration''',
      ));
  static String? accessToken(dynamic response) =>
      castToType<String>(getJsonField(
        response,
        r'''$.accessToken''',
      ));
  static String? refreshToken(dynamic response) =>
      castToType<String>(getJsonField(
        response,
        r'''$.refreshToken''',
      ));
  static String? name(dynamic response) => castToType<String>(getJsonField(
        response,
        r'''$.name''',
      ));
  static int? id(dynamic response) => castToType<int>(getJsonField(
        response,
        r'''$.id''',
      ));
  static List<String>? roles(dynamic response) => (getJsonField(
        response,
        r'''$.roles''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
  static int? instructorId(dynamic response) => castToType<int>(getJsonField(
        response,
        r'''$.instructorId''',
      ));
  static String? employeeRegistration(dynamic response) =>
      castToType<String>(getJsonField(
        response,
        r'''$.employeeRegistration''',
      ));
  static String? mensagemErro(dynamic response) =>
      castToType<String>(getJsonField(
        response,
        r'''$.message''',
      ));
}

class ChatBotEurinhoCall {
  static Future<ApiCallResponse> call({
    String? question = '',
    String? user = '',
  }) async {
    final ffApiRequestBody = '''
{
  "question": "$question",
  "overrideConfig": {
    "sessionId": "$user"
  }
}''';
    return ApiManager.instance.makeApiCall(
      callName: 'ChatBotEurinho',
      apiUrl:
          'https://charmed-wren-greatly.ngrok-free.app/api/v1/prediction/c3b8179f-6a4d-4f91-877c-c7bd3405f55e',
      callType: ApiCallType.POST,
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer iObJYB7iuZAax_-kOff2qtRp7WKbBMGFEeNTrmomPDo',
      },
      params: {},
      body: ffApiRequestBody,
      bodyType: BodyType.JSON,
      returnBody: true,
      encodeBodyUtf8: true,
      decodeUtf8: true,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  static String? answer(dynamic response) => castToType<String>(getJsonField(
        response,
        r'''$.text''',
      ));
  static List<String>? documents(dynamic response) => (getJsonField(
        response,
        r'''$.sourceDocuments[:].pageContent''',
        true,
      ) as List?)
          ?.withoutNulls
          .map((x) => castToType<String>(x))
          .withoutNulls
          .toList();
}

class RefreshTokenCall {
  static Future<ApiCallResponse> call({
    String? userName = '',
    String? token = '',
  }) async {
    return ApiManager.instance.makeApiCall(
      callName: 'RefreshToken',
      apiUrl:
          'https://closing-lively-woodcock.ngrok-free.app/auth/refresh/$userName',
      callType: ApiCallType.PUT,
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token',
      },
      params: {},
      bodyType: BodyType.NONE,
      returnBody: true,
      encodeBodyUtf8: false,
      decodeUtf8: false,
      cache: false,
      isStreamingApi: false,
      alwaysAllowBody: false,
    );
  }

  static double? expiration(dynamic response) =>
      castToType<double>(getJsonField(
        response,
        r'''$.expiration''',
      ));
  static String? accessToken(dynamic response) =>
      castToType<String>(getJsonField(
        response,
        r'''$.accessToken''',
      ));
  static String? refreshToken(dynamic response) =>
      castToType<String>(getJsonField(
        response,
        r'''$.refreshToken''',
      ));
  static String? employeeRegistration(dynamic response) =>
      castToType<String>(getJsonField(
        response,
        r'''$.employeeRegistration''',
      ));
  static double? created(dynamic response) => castToType<double>(getJsonField(
        response,
        r'''$.created''',
      ));
}

class ApiPagingParams {
  int nextPageNumber = 0;
  int numItems = 0;
  dynamic lastResponse;

  ApiPagingParams({
    required this.nextPageNumber,
    required this.numItems,
    required this.lastResponse,
  });

  @override
  String toString() =>
      'PagingParams(nextPageNumber: $nextPageNumber, numItems: $numItems, lastResponse: $lastResponse,)';
}

String _toEncodable(dynamic item) {
  return item;
}

String _serializeList(List? list) {
  list ??= <String>[];
  try {
    return json.encode(list, toEncodable: _toEncodable);
  } catch (_) {
    if (kDebugMode) {
      print("List serialization failed. Returning empty list.");
    }
    return '[]';
  }
}

String _serializeJson(dynamic jsonVar, [bool isList = false]) {
  jsonVar ??= (isList ? [] : {});
  try {
    return json.encode(jsonVar, toEncodable: _toEncodable);
  } catch (_) {
    if (kDebugMode) {
      print("Json serialization failed. Returning empty json.");
    }
    return isList ? '[]' : '{}';
  }
}

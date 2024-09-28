import 'package:flutter/material.dart';
import 'flutter_flow/request_manager.dart';
import 'backend/api_requests/api_manager.dart';

class FFAppState extends ChangeNotifier {
  static FFAppState _instance = FFAppState._internal();

  factory FFAppState() {
    return _instance;
  }

  FFAppState._internal();

  static void reset() {
    _instance = FFAppState._internal();
  }

  Future initializePersistedState() async {}

  void update(VoidCallback callback) {
    callback();
    notifyListeners();
  }

  String _tempSignatureBase64 = '';
  String get tempSignatureBase64 => _tempSignatureBase64;
  set tempSignatureBase64(String value) {
    _tempSignatureBase64 = value;
  }

  final _lastTrainingsManager = FutureRequestManager<ApiCallResponse>();
  Future<ApiCallResponse> lastTrainings({
    String? uniqueQueryKey,
    bool? overrideCache,
    required Future<ApiCallResponse> Function() requestFn,
  }) =>
      _lastTrainingsManager.performRequest(
        uniqueQueryKey: uniqueQueryKey,
        overrideCache: overrideCache,
        requestFn: requestFn,
      );
  void clearLastTrainingsCache() => _lastTrainingsManager.clear();
  void clearLastTrainingsCacheKey(String? uniqueKey) =>
      _lastTrainingsManager.clearRequest(uniqueKey);

  final _userInfoManager = FutureRequestManager<ApiCallResponse>();
  Future<ApiCallResponse> userInfo({
    String? uniqueQueryKey,
    bool? overrideCache,
    required Future<ApiCallResponse> Function() requestFn,
  }) =>
      _userInfoManager.performRequest(
        uniqueQueryKey: uniqueQueryKey,
        overrideCache: overrideCache,
        requestFn: requestFn,
      );
  void clearUserInfoCache() => _userInfoManager.clear();
  void clearUserInfoCacheKey(String? uniqueKey) =>
      _userInfoManager.clearRequest(uniqueKey);

  final _trainingsManager = FutureRequestManager<ApiCallResponse>();
  Future<ApiCallResponse> trainings({
    String? uniqueQueryKey,
    bool? overrideCache,
    required Future<ApiCallResponse> Function() requestFn,
  }) =>
      _trainingsManager.performRequest(
        uniqueQueryKey: uniqueQueryKey,
        overrideCache: overrideCache,
        requestFn: requestFn,
      );
  void clearTrainingsCache() => _trainingsManager.clear();
  void clearTrainingsCacheKey(String? uniqueKey) =>
      _trainingsManager.clearRequest(uniqueKey);
}

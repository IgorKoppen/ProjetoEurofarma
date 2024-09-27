// Automatic FlutterFlow imports
import '/backend/schema/structs/index.dart';
import '/actions/actions.dart' as action_blocks;
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'index.dart'; // Imports other custom actions
import '/flutter_flow/custom_functions.dart'; // Imports custom functions
import 'package:flutter/material.dart';
// Begin custom action code
// DO NOT REMOVE OR MODIFY THE CODE ABOVE!

import '/custom_code/actions/init_signature_controller.dart';
import 'dart:convert';
import 'dart:typed_data';

Future<String?> convertToBase64() async {
  final Uint8List? uint8list =
      await SignatureControllerSingleton().signatureController!.toPngBytes();
  String? imageEncoded = uint8list != null ? base64.encode(uint8list) : null;
  return imageEncoded;
}

// Automatic FlutterFlow imports
import '/backend/schema/structs/index.dart';
import '/actions/actions.dart' as action_blocks;
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import 'index.dart'; // Imports other custom widgets
import '/custom_code/actions/index.dart'; // Imports custom actions
import '/flutter_flow/custom_functions.dart'; // Imports custom functions
import 'package:flutter/material.dart';
// Begin custom widget code
// DO NOT REMOVE OR MODIFY THE CODE ABOVE!

import 'dart:convert';
import 'package:signature/signature.dart';
import '/custom_code/actions/init_signature_controller.dart';

class SignatureWidget extends StatefulWidget {
  const SignatureWidget({
    Key? key,
    this.width,
    this.height,
  }) : super(key: key);

  final double? width;
  final double? height;

  @override
  _SignatureWidgetState createState() => _SignatureWidgetState();
}

class _SignatureWidgetState extends State<SignatureWidget> {
  late SignatureController _signatureController;

  @override
  void initState() {
    super.initState();

    _signatureController = SignatureControllerSingleton().signatureController!;
    _signatureController.addListener(() => print('Value changed'));
  }

  @override
  void dispose() {
    _signatureController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return ClipRect(
      child: Signature(
        controller: _signatureController,
        backgroundColor: Colors.white,
        height: 160,
      ),
    );
  }
}

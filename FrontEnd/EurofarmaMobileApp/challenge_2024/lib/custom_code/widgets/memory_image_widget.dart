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

class MemoryImageWidget extends StatefulWidget {
  const MemoryImageWidget({
    Key? key,
    this.width,
    this.height,
    required this.encodedStr,
  }) : super(key: key);

  final double? width;
  final double? height;
  final String encodedStr;

  @override
  _MemoryImageWidgetState createState() => _MemoryImageWidgetState();
}

class _MemoryImageWidgetState extends State<MemoryImageWidget> {
  @override
  Widget build(BuildContext context) {
    return Image.memory(
      base64Decode(widget.encodedStr),
      fit: BoxFit.cover,
    );
  }
}

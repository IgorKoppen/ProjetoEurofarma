import 'dart:convert';
import 'dart:math' as math;

import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:intl/intl.dart';
import 'package:timeago/timeago.dart' as timeago;
import 'lat_lng.dart';
import 'place.dart';
import 'uploaded_file.dart';
import '/backend/schema/structs/index.dart';
import '/auth/custom_auth/auth_util.dart';

DateTime stringToDate(String expireToken) {
  final DateFormat formatter = DateFormat("dd/MM/yy HH:mm:ss,SSS");
  return formatter.parse(expireToken);
}

String? formatDate(String? date) {
  if (date == null) return null;
  try {
    // Create a DateFormat for the expected input format
    DateFormat inputFormat = DateFormat("dd/MM/yy HH:mm:ss,SSS");
    // Parse the input date string to a DateTime object
    DateTime parsedDate = inputFormat.parseStrict(date);
    // Format the DateTime object to the desired format
    String formattedDate = DateFormat('dd/MM/yyyy HH:mm').format(parsedDate);
    return formattedDate;
  } catch (e) {
    // Return null if the input date is not a valid format
    return null;
  }
}

String checkHexColorBrightness(String hexColor) {
  hexColor = hexColor.replaceFirst('#', '');
  int hex = int.parse(hexColor, radix: 16);
  int r = (hex >> 16) & 0xFF;
  int g = (hex >> 8) & 0xFF;
  int b = hex & 0xFF;
  double brightness = (r * 299 + g * 587 + b * 114) / 1000;

  if (brightness > 128) {
    return '#000000';
  } else {
    return '#FFFFFF';
  }
}

dynamic convertListToJsonUpdate(
  List<OpcoesQuizStruct>? customDataTypeList,
  String correctAnswer,
) {
  if (customDataTypeList == null) {
    return null;
  }

  List<Map<String, dynamic>> mappedList = [];

  for (OpcoesQuizStruct obj in customDataTypeList) {
    mappedList.add({
      'answer': obj.optionName,
      'isCorrect': obj.optionName == correctAnswer,
    });
  }

  return mappedList;
}

String? colorToString(Color? selectColor) {
  return selectColor != null
      ? '#${selectColor.value.toRadixString(16).padLeft(8, '0').substring(2)}'
      : null;
}

List<String>? nameAndSurname(
  List<String>? name,
  List<String>? surname,
) {
  // Receive a list of arguments name and surname. After concat the respective names and surnames and return them with the same order
  if (name == null || surname == null || name.isEmpty || surname.isEmpty) {
    return null;
  }
  final List<String> result = [];
  for (int i = 0; i < math.min(name.length, surname.length); i++) {
    result.add('${name[i]} ${surname[i]}');
  }
  return result;
}

String? timestampToSend(DateTime? timestamp) {
  // Now change this function to parse only the last two digits of the year in the order dd/MM/yy   if (timestamp == null) return null;  final formatter = DateFormat('yyyy/MM/dd HH:mm:ss,SSS');  return formatter.format(timestamp).toString();
  if (timestamp == null) return null;
  final formatter = DateFormat('dd/MM/yyyy HH:mm:ss,SSSSSSSSS');
  return formatter.format(timestamp).toString();
}

bool? isTokenExpired(DateTime expiredTokenDate) {
  if (expiredTokenDate == null) {
    throw ArgumentError('Expired token date cannot be null.');
  }
  return DateTime.now().isAfter(expiredTokenDate);
}

bool dateIsPast(String dateString) {
  try {
    // Create a DateFormat for the expected input format
    DateFormat inputFormat = DateFormat("dd/MM/yy HH:mm:ss,SSS");
    // Parse the input date string to a DateTime object
    DateTime date = inputFormat.parseStrict(dateString);
    // Check if the date is before the current date and time
    return date.isBefore(DateTime.now());
  } catch (e) {
    // Handle the error (optional) and assume date is not past if format is incorrect
    return false;
  }
}

String firstLetterOfFullname(String fullname) {
  if (fullname.isEmpty) {
    return "";
  }
  List<String> parts = fullname.split(" ");
  String firstLetterOfName = parts[0][0];

  if (parts.length < 2) {
    return firstLetterOfName.toUpperCase();
  }

  String firstLetterOfFirstSurname = parts[1][0];

  return (firstLetterOfName + firstLetterOfFirstSurname).toUpperCase();
}

DateTime stringToDateLogIn(double expireToken) {
  return DateTime.fromMillisecondsSinceEpoch((expireToken * 1000).toInt());
}

int? counter(int number) {
  // create a function that sums 1 when called from an argument and return the number
  number++;
  return number;
}

int? doubleToInt(double number) {
  // Receive a double number and convert to Int
  return number.toInt();
}

dynamic convertListToJson(
  List<OpcoesQuizStruct>? customDataTypeList,
  String correctAnswer,
) {
  if (customDataTypeList == null) {
    return null;
  }

  // Converta a lista diretamente para uma lista de mapas
  List<Map<String, dynamic>> mappedList = customDataTypeList.map((obj) {
    return {
      'answer': obj.optionName,
      'isCorrect':
          obj.optionName == correctAnswer, // Comparando com a resposta correta
    };
  }).toList();

  // Não transforme em string JSON, retorne como lista
  return mappedList;
}

String? intToString(int? number) {
  // Do this code receive a integer number and return it as string
  return number?.toString();
}

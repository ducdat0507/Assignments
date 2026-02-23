import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_application_5/models/post.dart';
import 'package:http/http.dart' as http;

class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  late Future<List<Post>> postsFuture;

  Future<List<Post>> getPosts() async {
    var postRequest = await http.get(
      Uri.parse("https://jsonplaceholder.typicode.com/posts/"),
    );

    if (postRequest.statusCode == 200) {
      return (jsonDecode(postRequest.body) as List<Map<String, dynamic>>)
          .map((e) => Post.fromJson(e))
          .toList();
    } else {
      throw Exception("Load failed (status ${postRequest.statusCode})");
    }
  }

  @override
  void initState() {
    super.initState();
    postsFuture = getPosts();
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: postsFuture,
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          var data = snapshot.requireData;
          return ListView.builder(
            itemCount: data.length,
            itemBuilder: (context, index) =>
                (ListTile(title: Text(data[index].title))),
          );
        } else if (snapshot.hasError) {
          return Text("${snapshot.error}");
        }

        return CircularProgressIndicator();
      },
    );
  }
}

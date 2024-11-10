### http://localhost:8092/getAllCharacters
OUTPUT:
```
{
    "data": [
        {
            "id": "6721936b66f6ba5bf5e4a6d9",
            "name": "Rat",
            "icon": "rat-icon-url",
            "creationDate": "2024-10-30T02:01:15.011+00:00",
            "description": "Funny rat character",
            "artist": {
                "id": "67218f323ca569084a760d6c",
                "name": "Kritika",
                "email": "kritikagupta2610@gmail.com",
                "description": "I am a born artist"
            }
        }
    ],
    "message": "Characters retrieved successfully",
    "status": 200
}
```
###http://localhost:8092/searchStories/Story
OUTPUT:
```
{
    "data": [
        {
            "id": "672198993fb0ea4a69c49de6",
            "title": "StoryA",
            "images": [
                "img1.jpg",
                "img2.jpg"
            ],
            "likeCount": 0,
            "description": "Story of a funny rat",
            "creationDate": "2024-10-30T02:23:21.949+00:00",
            "character": {
                "id": "6721936b66f6ba5bf5e4a6d9",
                "name": "Rat",
                "icon": "rat-icon-url",
                "creationDate": "2024-10-30T02:01:15.011+00:00",
                "description": "Funny rat character",
                "artist": {
                    "id": "67218f323ca569084a760d6c",
                    "name": "Kritika",
                    "email": "kritikagupta2610@gmail.com",
                    "description": "I am a born artist"
                }
            }
        },
        {
            "id": "67219cd1e98f7d2647d4ffd2",
            "title": "StoryB",
            "images": [
                "img1.jpg",
                "img2.jpg"
            ],
            "likeCount": 2,
            "description": "Story of a funny mouse",
            "creationDate": "2024-10-30T02:41:21.493+00:00",
            "character": {
                "id": "6721936b66f6ba5bf5e4a6d9",
                "name": "Rat",
                "icon": "rat-icon-url",
                "creationDate": "2024-10-30T02:01:15.011+00:00",
                "description": "Funny rat character",
                "artist": {
                    "id": "67218f323ca569084a760d6c",
                    "name": "Kritika",
                    "email": "kritikagupta2610@gmail.com",
                    "description": "I am a born artist"
                }
            }
        },
        {
            "id": "67219ef8e98f7d2647d4ffd3",
            "title": "Jim",
            "images": [
                "img1.jpg",
                "img2.jpg"
            ],
            "likeCount": 0,
            "description": "Story of a funny jackal",
            "creationDate": "2024-10-30T02:50:32.839+00:00",
            "character": {
                "id": "6721936b66f6ba5bf5e4a6d9",
                "name": "Rat",
                "icon": "rat-icon-url",
                "creationDate": "2024-10-30T02:01:15.011+00:00",
                "description": "Funny rat character",
                "artist": {
                    "id": "67218f323ca569084a760d6c",
                    "name": "Kritika",
                    "email": "kritikagupta2610@gmail.com",
                    "description": "I am a born artist"
                }
            }
        }
    ],
    "message": "Stories retrieved successfully",
    "status": 200
}

```
### http://localhost:8092/isCharacterNameExists/rat
OUTPUT:
```
{
    "data": true,
    "message": "Character name existence check completed",
    "status": 200
}
OR
{
    "data": false,
    "message": "Character name existence check completed",
    "status": 200
}
```
### http://localhost:8092/isStoryTitleExists/StoryA
OUTPUT:
```
{
    "data": true,
    "message": "Title existence check completed",
    "status": 200
}
```
###http://localhost:8092/getStoriesUnderCharacter/6721936b66f6ba5bf5e4a6d9
OUTPUT:
```
{
    "data": [
        {
            "id": "672198993fb0ea4a69c49de6",
            "title": "StoryA",
            "images": [
                "img1.jpg",
                "img2.jpg"
            ],
            "likeCount": 0,
            "description": "Story of a funny rat",
            "creationDate": "2024-10-30T02:23:21.949+00:00",
            "character": {
                "id": "6721936b66f6ba5bf5e4a6d9",
                "name": "Rat",
                "icon": "rat-icon-url",
                "creationDate": "2024-10-30T02:01:15.011+00:00",
                "description": "Funny rat character",
                "artist": {
                    "id": "67218f323ca569084a760d6c",
                    "name": "Kritika",
                    "email": "kritikagupta2610@gmail.com",
                    "description": "I am a born artist"
                }
            }
        },
        {
            "id": "67219cd1e98f7d2647d4ffd2",
            "title": "StoryB",
            "images": [
                "img1.jpg",
                "img2.jpg"
            ],
            "likeCount": 2,
            "description": "Story of a funny mouse",
            "creationDate": "2024-10-30T02:41:21.493+00:00",
            "character": {
                "id": "6721936b66f6ba5bf5e4a6d9",
                "name": "Rat",
                "icon": "rat-icon-url",
                "creationDate": "2024-10-30T02:01:15.011+00:00",
                "description": "Funny rat character",
                "artist": {
                    "id": "67218f323ca569084a760d6c",
                    "name": "Kritika",
                    "email": "kritikagupta2610@gmail.com",
                    "description": "I am a born artist"
                }
            }
        },
        {
            "id": "67219ef8e98f7d2647d4ffd3",
            "title": "Jim",
            "images": [
                "img1.jpg",
                "img2.jpg"
            ],
            "likeCount": 0,
            "description": "Story of a funny jackal",
            "creationDate": "2024-10-30T02:50:32.839+00:00",
            "character": {
                "id": "6721936b66f6ba5bf5e4a6d9",
                "name": "Rat",
                "icon": "rat-icon-url",
                "creationDate": "2024-10-30T02:01:15.011+00:00",
                "description": "Funny rat character",
                "artist": {
                    "id": "67218f323ca569084a760d6c",
                    "name": "Kritika",
                    "email": "kritikagupta2610@gmail.com",
                    "description": "I am a born artist"
                }
            }
        },
        {
            "id": "67219f20e98f7d2647d4ffd4",
            "title": "Dog",
            "images": [
                "img1.jpg",
                "img2.jpg"
            ],
            "likeCount": 0,
            "description": "funny dog",
            "creationDate": "2024-10-30T02:51:12.312+00:00",
            "character": {
                "id": "6721936b66f6ba5bf5e4a6d9",
                "name": "Rat",
                "icon": "rat-icon-url",
                "creationDate": "2024-10-30T02:01:15.011+00:00",
                "description": "Funny rat character",
                "artist": {
                    "id": "67218f323ca569084a760d6c",
                    "name": "Kritika",
                    "email": "kritikagupta2610@gmail.com",
                    "description": "I am a born artist"
                }
            }
        }
    ],
    "message": "Stories retrieved successfully",
    "status": 200
}


IF http://localhost:8092/getStoriesUnderCharacter/6
{
    "data": null,
    "message": "Invalid storyId: Must not be null, empty, whitespaces or incorrect format.",
    "status": 400
}
```
###http://localhost:8092/incrementLikeCount/6
```
{
"data": null,
"message": "Invalid storyId: Must not be null, empty, whitespaces or incorrect format.",
"status": 400
}

```
###http://localhost:8092/incrementLikeCount/67219cd1e98f7d2647d4ffd2
```
{
    "data": 5,
    "message": "Like count incremented successfully.",
    "status": 200
}

```
###http://localhost:8092/updateArtist
INPUT:
```
{
    "artistId":"67218f323ca569084a760d6c",
    "name":"Kritika",
    "description":"I am a born artist. I love drawing cartoons!"
}

```
OUTPUT:
```
{
    "data": {
        "id": "67218f323ca569084a760d6c",
        "name": "Kritika",
        "email": "kritikagupta2610@gmail.com",
        "description": "I am a born artist. I love drawing cartoons!"
    },
    "message": "Artist updated successfully",
    "status": 200
}

```
INPUT:
```
{
    "artistId":"67218f323ca569084a760d6c",
    "name":"Kritikagupta2610",
    "description": null
}

```
OUTPUT:
```
{
    "data": {
        "id": "67218f323ca569084a760d6c",
        "name": "Kritikagupta2610",
        "email": "kritikagupta2610@gmail.com",
        "description": "I am a born artist. I love drawing cartoons!"
    },
    "message": "Artist updated successfully",
    "status": 200
}

```
INPUT:
```
{
    "artistId":null,
    "name":"Kritikagupta2610",
    "description": null
}

```
OUTPUT:
```
{
    "data": {
        "artistId": "Artist Id is required"
    },
    "message": "Validation failed",
    "status": 400
}

```
###http://localhost:8092/addStoryToCharacter
INPUT:
```
{
    "characterId": "6721936b66f6ba5bf5e4a6d9",
    "title": "Dog in the garden",
    "images": ["img1.jpg", "img2.jpg"],
    "description": "funny dog explores neighbour's garden"
}

```
OUTPUT:
```
{
    "data": {
        "id": "672ed7afaefa377c0f541bd1",
        "title": "Dancing Frog",
        "images": [
            "img1.jpg",
            "img2.jpg"
        ],
        "likeCount": 0,
        "description": "funny dog explores neighbour's garden",
        "creationDate": "2024-11-09T03:31:59.576+00:00",
        "character": {
            "id": "6721936b66f6ba5bf5e4a6d9",
            "name": "Rat",
            "icon": "rat-icon-url",
            "creationDate": "2024-10-30T02:01:15.011+00:00",
            "description": "Funny rat character",
            "artist": {
                "id": "67218f323ca569084a760d6c",
                "name": "Kritikagupta2610",
                "email": "kritikagupta2610@gmail.com",
                "description": "I am a born artist. I love drawing cartoons!"
            }
        }
    },
    "message": "Story added to character successfully",
    "status": 201
}
```
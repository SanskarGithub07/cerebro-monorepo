import os
from flask import Flask, request, jsonify
from youtube_transcript_api import YouTubeTranscriptApi, TranscriptsDisabled, NoTranscriptFound
from flask_cors import CORS
from dotenv import load_dotenv

load_dotenv()

app = Flask(__name__)
CORS(app)

@app.route('/api/get-transcript', methods=['POST'])
def get_transcript():
    data = request.get_json()
    video_id = data.get("videoId")
    
    if not video_id:
        return jsonify({"error": "Missing video id"}), 400
    
    try:
        transcript = YouTubeTranscriptApi.get_transcript(video_id)
        return jsonify({"videoId": video_id, "transcript": transcript})
    except TranscriptsDisabled:
        return jsonify({"error": "Transcripts are disabled for this video."}), 403
    except NoTranscriptFound:
        return jsonify({"error": "Transcript not found."}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 500
    
if __name__ == '__main__':
    app.run(
        host=os.getenv("FLASK_HOST", "127.0.0.1"),
        port=os.getenv("FLASK_PORT", 5001)
    )
    
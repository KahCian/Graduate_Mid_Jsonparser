import json

with open('C:\\Users\\USER\\IdeaProjects\\MiddleWare\\src\\MiddleWare\\test.json', 'r') as f :
    json_data = json.load(f)
print(json.dumps(json_data, indent="\t"))
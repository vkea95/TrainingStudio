import os
import shutil
import json
import re
import urllib.parse

WORK_FOLDER = "~/Documents/ankiLib/todo"
TARGET_FOLDER = "~/Library/Application Support/Anki2/User 1/collection.media"
IPA = " "
VIDEO_LINK = " "
WHAT_ABOUT_HISTORY_PATH = os.path.join(WORK_FOLDER, "what_about_history.json")
FILE_PATTERN = ["tom-{0}", "{:0>2d}"]

# CSV: sentence, what_about, IPA, VIDEO_LINK,youglish,sound_01,sound_02
# v2: 追加slow_sound into template
# v3: 追加youglish 链接，模板：<a id="yg-widget-0" class="youglish-widget" data-query="great%20power" data-lang="english" data-accent="us" data-components="8392" data-auto-start="0"  rel="nofollow" href="https://youglish.com">Visit YouGlish.com</a>
# <script async src="https://youglish.com/public/emb/widget.js" charset="utf-8"></script>
YOUGLISH_TEMPLATE = '<a id=""yg-widget-0"" class=""youglish-widget"" data-query=""{0}"" data-lang=""english"" data-accent=""us"" data-components=""8392"" data-auto-start=""0""  rel=""nofollow"" href=""https://youglish.com"">Visit YouGlish.com</a><script async src=""https://youglish.com/public/emb/widget.js"" charset=""utf-8""></script>'


# TODO: video embed
# <iframe width="360" height="240" src="https://www.youtube.com/embed/ykvXyJo2AMI?start=5" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
def read_json(file_path):
    with open(file_path, "r") as json_file:
        return json.load(json_file)


def write_json(json_data, file_path):
    with open(file_path, "w") as json_file:
        json.dump(json_data, json_file)


def get_what_about_dict_from_work_folder(crt_path):
    what_about_dict = dict()
    for root, subdirs, files in os.walk(crt_path):
        for subdir in subdirs:
            what_about_dict[subdir] = dict()
            what_about_dict[subdir]['dir'] = os.path.join(root, subdir)
    return what_about_dict


def get_file_list(crt_path, filter_suffix, exclude=None):
    file_list = list()
    for root, subdirs, files in os.walk(crt_path):
        for name in files:
            if exclude is not None and exclude in name:
                continue
            if name.lower().endswith(filter_suffix):
                file_list.append(name)

    return file_list


def get_youglish_api(query_word):
    # return YOUGLISH_TEMPLATE.format(urllib.parse.quote(query_word))
    return " "


# def create_csv_record_for_word(what_about, what_about_path, file_list):
#     csv_content = list()
#     for i in range(len(file_list)):
#         word_item = file_list[i]
#         word = word_item.split(".")[0]
#         csv_record_item_list = [word, what_about, IPA, VIDEO_LINK, get_youglish_api(word)]
#         for j in range(0, len(word_item) - 1):
#             speed_title = 'slow' if 'slow' in word_item[j] else "normal"
#             target_file_name = "-".join([FILE_PATTERN[0].format(what_about),
#                                          FILE_PATTERN[1].format(i),
#                                          speed_title]) + "." + word_item[j].split(".")[-1]
#
#             sound = f"[sound:{target_file_name}]"
#             csv_record_item_list.append(sound)
#             # shutil.copy(os.path.join(WORK_FOLDER, what_about_path, file_list[i]),
#             #             os.path.join(TARGET_FOLDER, target_file_name))
#         csv_content.append(",".join(csv_record_item_list))
#     return csv_content


def create_csv_record_for_word(what_about, what_about_path, sentence_index_dict):
    csv_content = list()
    count = 0
    for key in sentence_index_dict.keys():
        sentence_index_dict[key].append(key)
        if len(sentence_index_dict[key]) < 3:
            print(f"{sentence_index_dict[key]} doesn't have the sentence!!!!!!!!!")
            continue
        csv_record_item_list = [key.split(".")[-1], what_about, IPA, VIDEO_LINK, get_youglish_api(key)]
        for i in range(0, len(sentence_index_dict[key]) - 1):
            file_name = sentence_index_dict[key][i]
            speed_title = 'slow' if 'slow' in file_name else "normal"
            target_file_name = "-".join([FILE_PATTERN[0].format(what_about),
                                         FILE_PATTERN[1].format(count),
                                         speed_title]) + "." + file_name.split(".")[-1]
            sound = f"[sound:{target_file_name}]"
            csv_record_item_list.append(sound)
            shutil.copy(os.path.join(WORK_FOLDER, what_about_path, file_name),
                        os.path.join(TARGET_FOLDER, target_file_name))
        count += 1
        csv_content.append(",".join(csv_record_item_list))

    return csv_content


def create_csv_record_for_sentence(what_about, what_about_path, sentence_index_dict):
    csv_content = list()
    for key in sentence_index_dict.keys():
        if len(sentence_index_dict[key]) < 3:
            print(f"{sentence_index_dict[key]} doesn't have the sentence!!!!!!!!!")
            continue
        sentence = sentence_index_dict[key][-1]
        csv_record_item_list = [sentence, what_about, IPA, VIDEO_LINK, get_youglish_api(sentence)]
        for i in range(0, len(sentence_index_dict[key]) - 1):
            file_name = sentence_index_dict[key][i]
            speed_title = 'slow' if 'slow' in file_name else "normal"
            target_file_name = "-".join([FILE_PATTERN[0].format(what_about),
                                         FILE_PATTERN[1].format(int(key)),
                                         speed_title]) + "." + file_name.split(".")[-1]
            sound = f"[sound:{target_file_name}]"
            csv_record_item_list.append(sound)
            shutil.copy(os.path.join(WORK_FOLDER, what_about_path, file_name),
                        os.path.join(TARGET_FOLDER, target_file_name))
        csv_content.append(",".join(csv_record_item_list))

    return csv_content


def output_file(file_path, content_list):
    with open(file_path, 'w') as f:
        for item in content_list:
            f.write("%s\n" % item)


def retrieve_sentence_index_dict(file_list):
    result_dict = dict()
    # the number of sentences is bigger than 2
    star_index = 0
    file_list.sort()
    if file_list[0][:3] == file_list[len(file_list) - 1][:3]:
        star_index = 3
    for item in file_list:
        key_name = item[star_index:]
        key_name = key_name.split(".")[0]
        key_name = re.findall(r"\d+", key_name)[0]
        value = result_dict.get(key_name, [])
        value.append(item)
        result_dict[key_name] = value
    return result_dict


def retrieve_word_index_dict(file_list):
    temp_dict = dict()
    for item in file_list:
        key_name = item.split(".")[0]
        key_name = key_name.replace("-slow", "")
        value = temp_dict.get(key_name, [])
        value.append(item)
        temp_dict[key_name] = value
    result_dict = dict()
    count = 0
    for key, value in temp_dict.items():
        key_name = ".".join(["{:0>2d}".format(count), key])
        result_dict[key_name] = value
        count += 1

    return result_dict


def retrieve_sentence_from_input_file(sentence_index_dict, file_path):
    with open(file_path, 'r') as f:
        index = 1
        for line in f.readlines():
            line = line.strip()
            if len(line) > 3:
                str_index = "{:0>2d}".format(index)
                if str_index in sentence_index_dict.keys():
                    line_content = "\"" + line.rstrip("\n") + "\""
                    sentence_index_dict[str_index].append(line_content)

                index += 1


def handle_what_about(what_about, what_about_path):
    file_list = get_file_list(what_about_path, ".txt")
    if len(file_list) > 0:
        #         sentence model
        file_list = sorted(get_file_list(what_about_path, ".mp3", None))
        file_list.reverse()
        sentence_index_dict = retrieve_sentence_index_dict(file_list)
        txt_file_list = get_file_list(what_about_path, ".txt")
        retrieve_sentence_from_input_file(sentence_index_dict, os.path.join(what_about_path, txt_file_list[0]))
        csv_content_list = create_csv_record_for_sentence(what_about, what_about_path, sentence_index_dict)
    else:
        #         word model
        file_list = sorted(get_file_list(what_about_path, ".mp3", None))
        file_list.reverse()
        word_index_dict = retrieve_word_index_dict(file_list)
        csv_content_list = create_csv_record_for_word(what_about, what_about_path, word_index_dict)
        # csv_content_list = create_csv_record_for_word(what_about, what_about_path, word_index_dict)
    file_path = os.path.join(WORK_FOLDER, what_about + ".csv")
    output_file(file_path, csv_content_list)


def main_entrance():
    what_about_history = read_json(WHAT_ABOUT_HISTORY_PATH) if os.path.exists(WHAT_ABOUT_HISTORY_PATH) else []
    what_about_dict = get_what_about_dict_from_work_folder(WORK_FOLDER)
    for what_about in what_about_dict.keys():
        if what_about in what_about_history:
            continue
        what_about_history.append(what_about)
        handle_what_about(what_about, what_about_dict[what_about]['dir'])

    write_json(what_about_history, WHAT_ABOUT_HISTORY_PATH)


main_entrance()

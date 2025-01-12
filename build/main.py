#!/usr/bin/env python3

"""build client & server bundles"""

import argparse
# if there is a problem with building, please let htmlcsjs know
import os
import shutil
import subprocess
import zipfile

import requests

# Used to run questbook.py
import questbook

import download

# used to map a projects classId on curseforge to a folder
class_id_to_dir = {
    6: "mods",
    12: "resourcepacks",
    -1: "other"
}


def parse_args():
    parser = argparse.ArgumentParser(prog="build", description=__doc__)
    parser.add_argument("--sha", action="store_true",
                        help="append git hash to zips")
    parser.add_argument("--name", type=str, help="append name to zips")
    parser.add_argument("--retries", type=int, default=3,
                        help="download attempts before failure")
    parser.add_argument("--clean", action="store_true",
                        help="clean output dirs")
    parser.add_argument("--dev_build", action="store_true",
                        help="makes a folder with all the files symlinked for development. probally only works on linux")
    parser.add_argument("-c", "--client", action="store_true",
                        help="only builds the client pack")
    return parser.parse_args()

basePath = os.path.normpath(os.path.realpath(__file__)[:-7] + "..")

def build(args):

    # Run questbook.py first
    questbook.build(questbook.parse_args())

    os.makedirs('./buildOut/', exist_ok=True)

    export_client_pack() # Client
    export_server_pack() # Server
    export_modlist() # Modlist

    print("done")

def export_client_pack():
    print("Client Pack Exporting")
    subprocess.run(['chmod', '+x', './packwiz'], check=True)
    subprocess.run(['./packwiz', 'curseforge', 'export', '-o', 'client.zip'], check=True)
    shutil.move('./client.zip', './buildOut/')
    print("Client Pack Export Done")

def export_server_pack():
    print("Server Pack Exporting")
    server_pack = "server.zip"

    download.download()

    shutil.copy("LICENSE", "build/server/LICENSE")

    os.chdir("build/server")
    subprocess.run(['java', '-jar', 'packwiz-installer-bootstrap.jar', '../../pack.toml'], check=True)

    with zipfile.ZipFile(server_pack, 'w') as zipf:
        for folder in ['config', 'groovy', 'libraries', 'mods', 'structures']:
            for root, _, files in os.walk(folder):
                for file in files:
                    file_path = os.path.join(root, file)
                    arcname = os.path.relpath(file_path, start='.')
                    zipf.write(file_path, arcname)

        for file in ['launch.sh', 'forge-1.12.2-14.23.5.2860.jar', 'LICENSE', 'minecraft_server.1.12.2.jar']:
            zipf.write(file, file)
            os.remove(file)

    os.chdir("../..")
    shutil.move(f"build/server/{server_pack}", f"buildOut/{server_pack}")
    print("Server Pack Export Done")

def export_modlist():
    print("Modlist Exporting")
    result = subprocess.run(['./packwiz', 'list'], capture_output=True, text=True).stdout.strip().split('\n')
    with open(basePath + "/buildOut/modlist.html", "w") as file:
        data = "<html><body><h1>Modlist</h1><ul>"
        for mod in result:
            data += "<li>" + mod + "</li>"
        data += "</ul></body></html>"
        file.write(data)
    print("Modlist Export Done")


if __name__ == "__main__":
    build(parse_args())

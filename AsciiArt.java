import java.util.Scanner;

public class AsciiArt {
    private String[] letters = new String[26];

    private void initLetterList() {
        for (char c = 'A'; c <= 'Z'; c++) {
            this.letters[c - 'A'] = String.valueOf(c);
        }
    }

    private void initAsciiArtList() {
        ;
    }

    private String removeSuffixSpaces(String s) {
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        return s.substring(0, i + 1);
    }

    private Boolean equal(String s1, String s2) {
        return removeSuffixSpaces(s1).equals(removeSuffixSpaces(s2));
    }

    private int findIndex(String[] ref, String[] target) {
        boolean is_found = true;
        for (int i = 0; i < ref.length; i++) {
            for (int j = 0; j < ref[i].length(); j++) {
                if (!equal(ref[i], target[j])) {
                    is_found = false;
                    break;
                }
                if (is_found) {
                    return i;
                }
            }
        }
        return -1;
    }

    private char findSingleLetter(String[] ascii_art) {
        // boolean flag = true;
        for (String[] std_ascii_art : this.raw_ascii_art) {
            int found_index = findIndex(std_ascii_art, ascii_art);
            if (found_index != -1) {
                return (char) (found_index + 'A');
            }
        }
        return ' ';
    }

    private String[][] splitAsciiArt(String[] lines) {
        String[][] result = new String[10][7];
        int line_length = lines[0].length();
        int[] split_points = new int[10];
        
        // if for all lines, line[i] == ' ', then split the ascii art
        for (int i = 0; i < line_length; i++) {
            boolean all_spaces = true;
            for (int j=0; j<7; j++) {
                if (lines[j].charAt(i) != ' ') {
                    all_spaces = false;
                    break;
                }
            }
            if (all_spaces) {
                split_points[0] = i;
                break;
            }
        }

        for (int i=0; i<10; i++) {
            if (split_points[i] == 0) {
                break;
            }
            for (int j = 0; j < 7; j++) {
                result[i][j] = lines[j].substring((i == 0 ? 0 : split_points[i-1] + 1), split_points[i]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        AsciiArt art = new AsciiArt();
        art.initLetterList();
        
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();

        for (int i = 0; i < amount; i++) {
            String[] scanned_lines = new String[7];
            for (int j = 0; j < 7; j++) {
                scanned_lines[j] = scanner.next();
                if (scanned_lines[j].equals("\n")) {
                    break;
                }
                String[][] ascii_arts = art.splitAsciiArt(scanned_lines);
                for (String[] ascii_art: ascii_arts) {
                    System.out.println(art.findSingleLetter(ascii_art));
                }
            }
            // System.out.println(art.findSingleLetter(ascii_art));
        }
    }

    private String[][] raw_ascii_art = {{"   ###    ", "  ## ##   ", " ##   ##  ", "##     ## ", "######### ", "##     ## ", "##     ## "}, {"########  ", "##     ## ", "##     ## ", "########  ", "##     ## ", "##     ## ", "########  "}, {" ######  ", "##    ## ", "##       ", "##       ", "##       ", "##    ## ", " ######  "}, {"########  ", "##     ## ", "##     ## ", "##     ## ", "##     ## ", "##     ## ", "########  "}, {"######## ", "##       ", "##       ", "######   ", "##       ", "##       ", "######## "}, {"######## ", "##       ", "##       ", "######   ", "##       ", "##       ", "##       "}, {" ######   ", "##    ##  ", "##        ", "##   #### ", "##    ##  ", "##    ##  ", " ######   "}, {"##     ## ", "##     ## ", "##     ## ", "######### ", "##     ## ", "##     ## ", "##     ## "}, {"#### ", " ##  ", " ##  ", " ##  ", " ##  ", " ##  ", "#### "}, {"      ## ", "      ## ", "      ## ", "      ## ", "##    ## ", "##    ## ", " ######  "}, {"##    ## ", "##   ##  ", "##  ##   ", "#####    ", "##  ##   ", "##   ##  ", "##    ## "}, {"##       ", "##       ", "##       ", "##       ", "##       ", "##       ", "######## "}, {"##     ## ", "###   ### ", "#### #### ", "## ### ## ", "##     ## ", "##     ## ", "##     ## "}, {"##    ## ", "###   ## ", "####  ## ", "## ## ## ", "##  #### ", "##   ### ", "##    ## "}, {" #######  ", "##     ## ", "##     ## ", "##     ## ", "##     ## ", "##     ## ", " #######  "}, {"########  ", "##     ## ", "##     ## ", "########  ", "##        ", "##        ", "##        "}, {" #######  ", "##     ## ", "##     ## ", "##     ## ", "##  ## ## ", "##    ##  ", " ##### ## "}, {"########  ", "##     ## ", "##     ## ", "########  ", "##   ##   ", "##    ##  ", "##     ## "}, {" ######  ", "##    ## ", "##       ", " ######  ", "      ## ", "##    ## ", " ######  "}, {"######## ", "   ##    ", "   ##    ", "   ##    ", "   ##    ", "   ##    ", "   ##    "}, {"##     ## ", "##     ## ", "##     ## ", "##     ## ", "##     ## ", "##     ## ", " #######  "}, {"##     ## ", "##     ## ", "##     ## ", "##     ## ", " ##   ##  ", "  ## ##   ", "   ###    "}, {"##      ## ", "##  ##  ## ", "##  ##  ## ", "##  ##  ## ", "##  ##  ## ", "##  ##  ## ", " ###  ###  "}, {"##     ## ", " ##   ##  ", "  ## ##   ", "   ###    ", "  ## ##   ", " ##   ##  ", "##     ## "}, {"##    ## ", " ##  ##  ", "  ####   ", "   ##    ", "   ##    ", "   ##    ", "   ##    "}, {"######## ", "     ##  ", "    ##   ", "   ##    ", "  ##     ", " ##      ", "######## "}};
}
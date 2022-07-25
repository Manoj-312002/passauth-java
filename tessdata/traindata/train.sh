wrap() {
    for i in `seq 0 $1`; do
        echo "$2$i$3"
    done
}

N=0

# Uncomment this line if, you’re rerunning the script
# rm eng.pffmtable  eng.shapetable eng.traineddata eng.unicharset unicharset font_properties eng.inttemp eng.normproto *.tr *.txt

for i in `seq 0 $N`; do
    tesseract eng.arial.exp$i.png eng.arial.exp$i nobatch box.train
done

unicharset_extractor `wrap $N "eng.arial.exp" ".box"`
echo "arial 0 0 1 0 0" > font_properties # tell Tesseract informations about the font

mftraining –F font_properties –U unicharset –O eng.unicharset `wrap $N "eng.arial.exp" ".tr"`
cntraining `wrap $N "eng.arial.exp" ".tr"`

# rename all files created by mftraing en cntraining, add the prefix eng.:
    mv inttemp eng.inttemp
    mv normproto eng.normproto
    mv pffmtable eng.pffmtable
    mv shapetable eng.shapetable

combine_tessdata eng.
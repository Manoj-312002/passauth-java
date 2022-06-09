N=0
for i in `seq 0 $N`; do
    tesseract eng.arial.exp$i.png eng.arial.exp$i batch.nochop makebox
done
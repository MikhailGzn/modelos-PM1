
import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns

###########################################
#Con esto podes importar 
#import subprocess

# Ejecutar el comando pip
#result = subprocess.run(['python', '-m', 'pip', 'install', 'seaborn'], capture_output=True)

# Imprimir la salida del comando pip
#print(result.stdout)
###########################################
df = pd.read_csv('discrete-event-simulator\\src\\main\\java\\gida\\simulators\\labs\\results\\report.csv')
df.drop(df.columns[df.columns.str.contains('Tiempo de simulacion',case = False)],axis = 1, inplace = True)

# Draw a pointplot to show pulse as a function of three categorical factors
g = sns.catplot(
    data=df ,
    capsize=.1, palette="YlGnBu_d", errorbar="se",
    kind="point", height=6, aspect=3.)
plt.xticks(rotation=45)
g.despine(left=True)
# ahora lo guardo en una imagen
plt.savefig('discrete-event-simulator\\src\\main\\java\\gida\\simulators\\labs\\results\\report.png')
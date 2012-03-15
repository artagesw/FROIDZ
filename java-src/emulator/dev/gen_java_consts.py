from __future__ import with_statement
import os
import sys

# The name of the Java class to create
java_class_name = 'OPCODES'

# Path to the WP File that specifies the opcodes
spec_path = './TOASTY Asm.wp'

# Path to the java file that this program generates
out_path = '../cpu/' + java_class_name + '.java'

# The top of the java file
java_beginning = '''
public class {NAME}
{

'''

# The end of the java file
java_end = '\n}'

# Template for what each java opcode definition looks like. 
# {0} is opcode name, {1] is number
java_line = '    public static final int {0} = {1};\n'

def get_opcode(line):
    '''
    precondition: Line is not a comment
    precondition: Line is formulated correctly
    Takes a line from a WP file and returns opname, opcode
    { 'OPNAME' : 
    '''
    line = line.replace(' ', '').split('|')
    line[0] = line[0].replace('.', "_")
    return line[0], int(line[3][:8], 2)
    
if __name__ == '__main__':

    try:
        # Open the file we are going to create for writing
        out_f = open(out_path, 'w')

    except IOError:
        print "Could not find the output location."
        print "THIS SCRIPT MUST BE RUN IN THE DIRECTOR THAT IT LIVES"

    out_f.write(java_beginning.replace('{NAME}', java_class_name))

    num = 0

    with open(spec_path, 'r') as spec_f:
        for line in filter(lambda l: ('#' not in l and l != ''), map(lambda l: l.strip(), spec_f.readlines())):
            out_f.write(java_line.format(*get_opcode(line)))
            num += 1

    out_f.write(java_end)

    print str(num) + " Instructions Generated."

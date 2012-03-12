
java_class_name = 'Opcodes'

spec_path = './TOASTY Asm.wp'
out_path = './' + java_class_name + '.java'

java_beginning = '''
public class {NAME}
{

'''

java_end = '\n}'

java_line = '    public static final int {0} = {1};\n'

# Load the specification file
out_f = open(out_path, 'w')

def get_opcode(line):
    '''
    precondition: Line is not a comment
    precondition: Line is formulated correctly
    Takes a line from a WP file and returns opname, opcode
    { 'OPNAME' : 
    '''
    line = line.replace(' ', '').split('|')
    return line[0], int(line[3][:8], 2)
    
if __name__ == '__main__':
    out_f.write(java_beginning.replace('{NAME}', java_class_name))

    num = 0

    with open(spec_path, 'r') as spec_f:
        for line in filter(lambda l: ('#' not in l and l != ''), map(lambda l: l.strip(), spec_f.readlines())):
            out_f.write(java_line.format(*get_opcode(line)))
            num += 1

    out_f.write(java_end)

    print str(num) + " Instructions Generated."

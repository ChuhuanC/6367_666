package org.pitest.mutationtest.engine.gregor.mutators.TCZ;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
//import org.objectweb.asm.Type;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public class M1 implements MethodMutatorFactory {

//      M1_MUTATOR;

      @Override
      public MethodVisitor create(final MutationContext context,
                                  final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new M1Mutator(this, context, methodVisitor);
      }

      @Override
      public String getGloballyUniqueId() {
        return this.getClass().getName();
      }

      @Override
      public String getName() {
        return toString();
      }

    }

    class M1Mutator extends MethodVisitor {
        
        private final MutationContext      context;
        private final MethodMutatorFactory factory;
        
        public M1Mutator(final MethodMutatorFactory factory,
                final MutationContext context,
                final MethodVisitor delegateMethodVisitor) {
              super(Opcodes.ASM6, delegateMethodVisitor);
              this.context = context;
              this.factory = factory;
        }
        
        @Override
        public void visitFieldInsn(final int bytecode, String object, String name, String desc) {
           if (bytecode == Opcodes.GETFIELD) {
               Label flagnull = new Label();
               Label flagnnull = new Label();
               super.visitInsn(Opcodes.DUP);
               super.visitJumpInsn(Opcodes.IFNULL, flagnull);
               super.visitFieldInsn(bytecode, object, name, desc);
               super.visitJumpInsn(Opcodes.GOTO, flagnnull);
               super.visitLabel(flagnull);
               final MutationIdentifier mutationId = this.context.registerMutation(new M1(),
                     "M1: null dereference has been replaced by default value"); 
               getdefault(desc);
               super.visitLabel(flagnnull);
//           } else if (bytecode == Opcodes.PUTFIELD && Type.getType(desc).getSize() == 1) {
//               Label flagnull = new Label();
//               Label flagnnull = new Label();
//               super.visitInsn(Opcodes.DUP2);
//               super.visitJumpInsn(Opcodes.IFNULL, flagnull);
//               super.visitFieldInsn(bytecode, object, name, desc);
//               super.visitJumpInsn(Opcodes.GOTO, flagnnull);
//               super.visitLabel(flagnull);
//               final MutationIdentifier mutationId = this.context.registerMutation(new M1(),
//                       "M1: null dereference has been replaced by default value"); 
//               super.visitInsn(Opcodes.POP2);
//               super.visitLabel(flagnnull);
//           } else if (bytecode == Opcodes.PUTFIELD && Type.getType(desc).getSize() == 2) {
//               Label flagnull = new Label();
//               Label flagnnull = new Label();
//               super.visitInsn(Opcodes.DUP2_X1);
//               super.visitInsn(Opcodes.DUP2);
//               super.visitInsn(Opcodes.DUP2_X2);
//               super.visitJumpInsn(Opcodes.IFNULL, flagnull);
//               super.visitFieldInsn(bytecode, object, name, desc);
//               super.visitJumpInsn(Opcodes.GOTO, flagnnull);
//               super.visitLabel(flagnull);
//               final MutationIdentifier mutationId = this.context.registerMutation(new M1(),
//                       "M1: null dereference has been replaced by default value"); 
//               super.visitInsn(Opcodes.POP2);
//               super.visitInsn(Opcodes.POP);
//               super.visitLabel(flagnnull);
           } else {
               super.visitFieldInsn(bytecode, object, name, desc);
           }
       }
        
        public void getdefault(String desc) {
            if (desc.equals("I") || desc.equals("Z") || desc.equals("C") || desc.equals("B") || desc.equals("S")) {
                super.visitInsn(Opcodes.ICONST_0);
            }
           
        if (desc.equals("D")) {
                super.visitInsn(Opcodes.DCONST_0);
            } 
        if (desc.equals("F")) {
            super.visitInsn(Opcodes.FCONST_0);
            } 
        if (desc.equals("J")) {
            super.visitInsn(Opcodes.LCONST_0); 
               
        } else {
            super.visitInsn(Opcodes.ACONST_NULL);
        }
        }
   
        
}
